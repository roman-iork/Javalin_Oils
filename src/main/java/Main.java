import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import transfer.OilTransfer;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.javalin.rendering.template.TemplateUtil.model;


public class Main {

    private static HashMap<String, Double> oilWeights = new HashMap<>();
    private static List<String> chosenAcids = null;

    public static void main(String[] args) throws IOException {
        
        // Initialize database and create tables
        DatabaseInitializer.createTable();

        TemplateEngine templateEngine = TemplateEngine.create(
                new DirectoryCodeResolver(Path.of("src/main/resources/jte")),
                ContentType.Html
        );

        // Initialize the Javalin server
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.staticFiles.add("/static", Location.CLASSPATH);
            config.fileRenderer(new JavalinJte(templateEngine));
        }).start(8080);

        // ROOT // ROOT // ROOT // ROOT
        // Define a basic GET route at the root URL
        app.get("/", ctx -> {
            TemplateOutput output = new StringOutput();
            templateEngine.render("welcome.jte", null, output);
            ctx.result(String.valueOf(output)).contentType("Content-Type=Html");
        });

        // Dealing with oils // Dealing with oils // Dealing with oils // Dealing with oils
        // POST methods
        // Add a new oil
        app.post("/oils", ctx -> {
            String oilName = ctx.formParam("oil_name");
                if (oilName.isBlank() || oilName.isEmpty()) {
                    throw new Exception("No empty name allowed");
                }
            try (Connection connection = Database.getConnection()) {
                String sql = "INSERT INTO oils (oil_name) VALUES (?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, oilName);
                stmt.executeUpdate();

                ctx.status(201).redirect("/oils");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // delete complete oil
        app.post("/delete/{oilName}", ctx -> {
            String oilName = ctx.pathParam("oilName");

            try (Connection connection = Database.getConnection()) {
                String sqlOil = "DELETE FROM oils WHERE oil_name = ?";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.setString(1, oilName);
                stmt1.execute();

                ctx.status(404).redirect("/oils");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // remove oil from calculation
        app.post("/calculation/remove/one/{oilName}", ctx -> {
            String oilName = ctx.pathParam("oilName");

            try (Connection connection = Database.getConnection()) {
                String sqlOil = "UPDATE oils SET is_calculated = FALSE WHERE oil_name = ?";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.setString(1, oilName);
                stmt1.execute();
                oilWeights.remove(oilName);
                chosenAcids = null;
                ctx.status(404).redirect("/oils");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // remove all oils from calculation
        app.post("/calculation/remove/all", ctx -> {

            try (Connection connection = Database.getConnection()) {
                String sqlOil = "UPDATE oils SET is_calculated = FALSE WHERE is_calculated = TRUE";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.execute();
                oilWeights.clear();
                chosenAcids = null;
                ctx.status(404).redirect("/oils");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // add oil to calculation
        app.post("/calculation/{oilName}", ctx -> {
            String oilName = ctx.pathParam("oilName");

            try (Connection connection = Database.getConnection()) {
                String sqlOil = "UPDATE oils SET is_calculated = TRUE WHERE oil_name = ?";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.setString(1, oilName);
                stmt1.executeUpdate();
                ctx.status(201).redirect("/oils");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // searching oils
        app.post("/oil", ctx -> {
            String oilName = ctx.formParam("oil_name");
            ctx.status(200).redirect("/oils/" + oilName);
        });

        // GET methods
        // Define a route for all oils
        app.get("/oils", ctx -> {
            try (Connection connection = Database.getConnection()) {
                String sql = "SELECT * FROM oils";
                PreparedStatement stmt = connection.prepareStatement(sql);

                var allOilsList = new LinkedList<String>();
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    var oilName = resultSet.getString(2);
                    allOilsList.add(oilName);
                }

                String sqlCalc = "SELECT * FROM oils WHERE is_calculated = TRUE";
                stmt = connection.prepareStatement(sqlCalc);

                var calcOilsList = new LinkedList<String>();
                ResultSet calcResultSet = stmt.executeQuery();
                while (calcResultSet.next()) {
                    var oilName = calcResultSet.getString(2);
                    calcOilsList.add(oilName);
                }
                ctx.status(200).render("oils.jte", model("data", allOilsList, "calc", calcOilsList));
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Failed to fetch oils");
            }
        });

        // Define a route for a particular oil
        app.get("/oils/{name}", ctx -> {
            try (Connection connection = Database.getConnection()) {
                var oilName = ctx.pathParam("name");

                String sql = """
                        SELECT oil_name,
                            acid_name,
                            acid_amount
                        FROM oils o
                        LEFT JOIN oils_acids oa
                            ON o.id = oa.oil_id
                        LEFT JOIN acids a
                            ON a.id = oa.acid_id
                        where oil_name = ?
                    """;
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, oilName);
                var oilTransferList = new LinkedList<OilTransfer>();
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    var oilTransfer = new OilTransfer();
                    var acidName = resultSet.getString(2);
                    var acidAmount = resultSet.getDouble(3);
                    oilTransfer.setOilName(oilName);
                    oilTransfer.setAcidName(acidName);
                    oilTransfer.setAcidAmount(acidAmount);
                    oilTransferList.add(oilTransfer);
                }

                List<String> acids = new ArrayList<>();
                String sqlAcids = "SELECT acid_name FROM acids";
                stmt = connection.prepareStatement(sqlAcids);
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    var acidName = resultSet.getString(1);
                    acids.add(acidName);
                }

                ctx.status(200).render("oil.jte",
                        model("data", oilTransferList,
                                "acidsData", acids.stream().sorted().toList()
                        ));
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Failed to fetch oil data");
            }
        });

        // Dealing with acids // Dealing with acids // Dealing with acids // Dealing with acids
        // POST methods
        // Add a new acid
        app.post("/acids", ctx -> {
            String acidName = ctx.formParam("acid_name");
            String oilName = ctx.queryParam("oil");
            if (acidName.isBlank() || acidName.isEmpty()) {
                throw new Exception("No empty name allowed");
            }
            try (Connection connection = Database.getConnection()) {
                String sqlAcid = "INSERT INTO acids (acid_name) VALUES (?)";
                PreparedStatement stmt1 = connection.prepareStatement(sqlAcid);
                stmt1.setString(1, acidName);
                stmt1.executeUpdate();
                ctx.status(201).redirect("/oils/" + oilName);
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // Add acid amount(complete one acid)
        app.post("/{oilName}/acids", ctx -> {
            String oilName = ctx.pathParam("oilName");
            String acidName = ctx.formParam("acid_name");

            try (Connection connection = Database.getConnection()) {
                var acidAmount = Double.parseDouble(ctx.formParam("acid_amount"));
                String sqlOil = "SELECT * FROM oils WHERE oil_name = ?";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.setString(1, oilName);
                var resultSet1 = stmt1.executeQuery();
                var oilId = -1;
                if (resultSet1.next()) {
                    oilId = resultSet1.getInt(1);
                }

                String sqlAcid = "SELECT * FROM acids WHERE acid_name = ?";
                PreparedStatement stmt2 = connection.prepareStatement(sqlAcid);
                stmt2.setString(1, acidName);
                var resultSet2 = stmt2.executeQuery();
                var acidId = -1;
                if (resultSet2.next()) {
                    acidId = resultSet2.getInt(1);
                }

                String sqlOilAcid = "INSERT INTO oils_acids (oil_id, acid_id, acid_amount) VALUES (?, ?, ?)";
                PreparedStatement stmt3 = connection.prepareStatement(sqlOilAcid);
                stmt3.setInt(1, oilId);
                stmt3.setInt(2, acidId);
                stmt3.setDouble(3, acidAmount);
                stmt3.executeUpdate();

                ctx.status(201).redirect("/oils/" + oilName);
            } catch (NumberFormatException nfe) {
                ctx.result("Haha - amount should be filled");
            } catch (JdbcSQLIntegrityConstraintViolationException cve) {
                ctx.result("Haha - no the same combinations");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // delete complete acid
        app.post("/delete/{oilName}/{acidName}", ctx -> {
            String oilName = ctx.pathParam("oilName");
            String acidName = ctx.pathParam("acidName");

            try (Connection connection = Database.getConnection()) {
                String sqlOil = "SELECT * FROM oils WHERE oil_name = ?";
                PreparedStatement stmt1 = connection.prepareStatement(sqlOil);
                stmt1.setString(1, oilName);
                var resultSet1 = stmt1.executeQuery();
                var oilId = -1;
                if (resultSet1.next()) {
                    oilId = resultSet1.getInt(1);
                }

                String sqlAcid = "SELECT * FROM acids WHERE acid_name = ?";
                PreparedStatement stmt2 = connection.prepareStatement(sqlAcid);
                stmt2.setString(1, acidName);
                var resultSet2 = stmt2.executeQuery();
                var acidId = -1;
                if (resultSet2.next()) {
                    acidId = resultSet2.getInt(1);
                }

                String sqlDel = "DELETE FROM oils_acids WHERE oil_id = ? AND acid_id = ?";
                PreparedStatement stmt3 = connection.prepareStatement(sqlDel);
                stmt3.setInt(1, oilId);
                stmt3.setInt(2, acidId);
                stmt3.executeUpdate();
                ctx.status(200).redirect("/oils/" + oilName);
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });

        // Calculation // Calculation // Calculation // Calculation
        // do calculation
        // POST methods
        app.post("/calculation/do/all", ctx -> {
            var oilToAdjust = ctx.queryParam("oil");
            var adjustment = ctx.queryParam("weight");
            var currChosenAcids = ctx.formParamsAsClass("acid", String.class).get();
            if (currChosenAcids.size() == 2) {
                chosenAcids = currChosenAcids;
            }
            var clearChosen = ctx.queryParam("clear");
            if (clearChosen != null && clearChosen.equals("do")) {
                chosenAcids = null;
            }
            var weightStr = ctx.formParam("weight");
            var ratioStr = ctx.formParam("ratio");

            try (Connection connection = Database.getConnection()) {
                String sqlCalc = """
                        SELECT
                            oil_name,
                            acid_name,
                            acid_amount
                        FROM oils o
                        LEFT JOIN oils_acids oa ON o.id = oa.oil_id
                        LEFT JOIN acids a ON a.id = oa.acid_id
                        WHERE is_calculated = TRUE;
                    """;
                PreparedStatement stmt = connection.prepareStatement(sqlCalc);
                var calcOilsListData = new HashMap<String, HashMap<String, Double>>();
                ResultSet calcResultSet = stmt.executeQuery();
                while (calcResultSet.next()) {
                    var oilName = calcResultSet.getString(1);
                    var acidName = calcResultSet.getString(2);
                    var acidAmount = calcResultSet.getDouble(3);
                    var acidData = calcOilsListData.getOrDefault(oilName, new HashMap<>());
                    acidData.put(acidName, acidAmount);
                    calcOilsListData.put(oilName, acidData);
                }

                var allOils = calcOilsListData.keySet();

                for (var oil : allOils) {
                    oilWeights.computeIfAbsent(oil, k -> 2.00);
                }

                if (oilToAdjust != null) {
                    oilWeights.put(oilToAdjust, Double.parseDouble(adjustment));
                }

                Map<String, Double> weightRatioMap = new HashMap<>();
                if (ratioStr != null) {
                    var weight = Double.parseDouble(weightStr);
                    var ratio = Double.parseDouble(ratioStr);
                    var autoCountOilWeights = Calculation.calculateAuto(
                            calcOilsListData,
                            weight,
                            ratio,
                            List.of(chosenAcids.getFirst(), chosenAcids.getLast()),
                            oilWeights
                    );
                    if (autoCountOilWeights == null) {
                        throw new Exception("can't auto count try by hands (");
                    }
                    oilWeights = autoCountOilWeights;
                    weightRatioMap.put("w", weight);
                    weightRatioMap.put("r", ratio);
                }

                var acidsPercentage = Calculation.calculate(calcOilsListData, oilWeights);
                LinkedList<String> ratioList = null;
                if (chosenAcids != null && chosenAcids.size() == 2) {
                    var first = chosenAcids.getFirst();
                    var second = chosenAcids.getLast();
                    var mapOfTwo = new HashMap<String, Double>();
                    mapOfTwo.put(first, acidsPercentage.get(first));
                    mapOfTwo.put(second, acidsPercentage.get(second));
                    var sortedMap = mapOfTwo.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldValue, newValue) -> oldValue,
                                    LinkedHashMap::new));
                    ratioList = new LinkedList<>();
                    ratioList.add(sortedMap.sequencedKeySet().getFirst());
                    ratioList.add(sortedMap.sequencedKeySet().getLast());
                    ratioList.add(String.valueOf(sortedMap.sequencedValues().getFirst() / sortedMap.sequencedValues().getLast()));
                }
                ctx.status(201).render("calculation.jte",
                        model(
                                "weights", oilWeights,
                                "acids", acidsPercentage,
                                "selected", ratioList,
                                "wR", weightRatioMap,
                                "oilsInfo", calcOilsListData
                        ));
            } catch (Exception e) {
                e.printStackTrace();
                ctx.result(e.getMessage()).status(500);
            }
        });
    }
}
