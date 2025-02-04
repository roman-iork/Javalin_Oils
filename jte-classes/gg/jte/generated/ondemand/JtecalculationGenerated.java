package gg.jte.generated.ondemand;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.math.BigDecimal;
import java.math.MathContext;
@SuppressWarnings("unchecked")
public final class JtecalculationGenerated {
	public static final String JTE_NAME = "calculation.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,6,6,6,6,13,13,13,13,16,16,18,18,18,20,20,20,20,20,20,20,20,23,23,23,23,23,23,23,23,26,26,26,26,26,26,26,26,30,30,30,34,34,34,34,34,34,34,34,37,37,37,37,37,37,37,37,40,40,40,40,40,40,40,40,45,45,49,49,49,53,53,56,56,56,57,57,59,59,60,60,60,61,61,63,63,65,65,67,67,67,69,69,69,72,72,74,74,77,77,77,77,80,80,82,82,84,84,84,84,84,84,84,84,84,86,86,86,87,87,87,90,90,93,93,95,95,97,97,97,97,97,97,97,97,97,99,99,99,100,100,100,103,103,106,106,107,107,111,111,111,113,113,113,115,115,115,123,123,123,123,123,123,123,123,123,124,124,124,124,124,124,124,124,124,129,129,131,131,131,131,131,131,132,132,132,6,7,8,9,10,10,10,10};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, HashMap<String, Double> weights, Map<String, Double> acids, LinkedList<String> selected, Map<String, Double> wR, HashMap<String, HashMap<String, Double>> oilsInfo) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div style=\"padding-left: 30px\" class=\"rounded-rectangle-result-left\">\n        <h3 style=\"display: inline-block; padding: 10px\">Oils</h3>\n        ");
				for (var oilWeight : weights.entrySet()) {
					jteOutput.writeContent("\n            <span style=\"display: flex\">\n                <span style=\"padding-right: 20px\">");
					jteOutput.setContext("span", null);
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.writeContent("</span>\n                <div style=\"position: absolute; left: 30%\">\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() - 1.00);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">-1</button>\n                    </form>\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() - 0.10);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">-0.1</button>\n                    </form>\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() - 0.05);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">-0.05</button>\n                    </form>\n                    <span style=\"padding-left: 5px\">\n                        ");
					jteOutput.setContext("span", null);
					jteOutput.writeUserContent(BigDecimal.valueOf(oilWeight.getValue()).round(new MathContext(3)));
					jteOutput.writeContent("\n                    </span>\n                </div>\n                <div style=\"position: absolute; left: 50%\">\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() + 0.05);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">+0.05</button>\n                    </form>\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() + 0.10);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">+0.1</button>\n                    </form>\n                    <form style=\"display: inline; padding-left: 5px\" action=\"/calculation/do/all?oil=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getKey());
					jteOutput.setContext("form", null);
					jteOutput.writeContent("&weight=");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oilWeight.getValue() + 1.00);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\">\n                        <button type=\"submit\">+1</button>\n                    </form>\n                </div>\n            </span>\n        ");
				}
				jteOutput.writeContent("\n        <div style=\"padding-top: 30px; padding-bottom: 30px; padding-left: 10px\">\n            <b>\n                Total weight:\n                ");
				jteOutput.setContext("b", null);
				jteOutput.writeUserContent(BigDecimal.valueOf(weights.values().stream().mapToDouble(Double::doubleValue).sum()).round(new MathContext(4)));
				jteOutput.writeContent("\n            </b>\n        </div>\n        <span style=\"display: flex\">\n            ");
				for (var oneOilInfo : oilsInfo.entrySet()) {
					jteOutput.writeContent("\n                <div style=\"width: 30%; font-size: 13px; position: relative; left: 0%\">\n                    <h4>\n                        ");
					jteOutput.setContext("h4", null);
					jteOutput.writeUserContent(oneOilInfo.getKey());
					jteOutput.writeContent("\n                        ");
					if (selected != null) {
						jteOutput.writeContent("\n                            (\n                            ");
						if (oneOilInfo.getValue().get(selected.get(0)) != null && oneOilInfo.getValue().get(selected.get(1)) != null) {
							jteOutput.writeContent("\n                                ");
							jteOutput.setContext("h4", null);
							jteOutput.writeUserContent(BigDecimal.valueOf(oneOilInfo.getValue().get(selected.get(0)) / oneOilInfo.getValue().get(selected.get(1))).round(new MathContext(3)));
							jteOutput.writeContent("\n                            ");
						}
						jteOutput.writeContent("\n                            )\n                        ");
					}
					jteOutput.writeContent("\n                    </h4>\n                    ");
					for (var acidPair : oneOilInfo.getValue().entrySet()) {
						jteOutput.writeContent("\n                        <span style=\"display: flex\">\n                            ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(acidPair.getKey());
						jteOutput.writeContent("\n                             <span style=\"position: absolute; left: 70%\">\n                                ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(acidPair.getValue());
						jteOutput.writeContent("\n                            </span>\n                        </span>\n                    ");
					}
					jteOutput.writeContent("\n                </div>\n            ");
				}
				jteOutput.writeContent("\n        </span>\n    </div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div style=\"padding-left: 30px\" class=\"rounded-rectangle-result-right\">\n        <h3 style=\"display: inline-block; padding: 10px\">Acids</h3>\n        ");
				if (selected == null) {
					jteOutput.writeContent("\n            <form action=\"/calculation/do/all\" method=\"post\">\n                ");
					for (var acid : acids.entrySet()) {
						jteOutput.writeContent("\n                    <span style=\"display: flex\">\n                        <input type=\"checkbox\" name=\"acid\"");
						var __jte_html_attribute_0 = acid.getKey();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("input", "value");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                        <label>\n                            ");
						jteOutput.setContext("label", null);
						jteOutput.writeUserContent(acid.getKey());
						jteOutput.writeContent("\n                            <span style=\"position: absolute; left: 75%\">");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(acid.getValue());
						jteOutput.writeContent("</span>\n                        </label>\n                    </span>\n                ");
					}
					jteOutput.writeContent("\n                <button type=\"submit\">Submit</button>\n            </form>\n        ");
				} else {
					jteOutput.writeContent("\n            <form action=\"/calculation/do/all\" method=\"post\">\n                ");
					for (var acid : acids.entrySet()) {
						jteOutput.writeContent("\n                    <span style=\"display: flex\">\n                        <input type=\"checkbox\" name=\"acid\"");
						var __jte_html_attribute_1 = acid.getKey();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("input", "value");
							jteOutput.writeUserContent(__jte_html_attribute_1);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" disabled>\n                        <label>\n                            ");
						jteOutput.setContext("label", null);
						jteOutput.writeUserContent(acid.getKey());
						jteOutput.writeContent("\n                            <span style=\"position: absolute; left: 75%\">");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(acid.getValue());
						jteOutput.writeContent("</span>\n                        </label>\n                    </span>\n                ");
					}
					jteOutput.writeContent("\n                <button type=\"submit\">Submit</button>\n            </form>\n        ");
				}
				jteOutput.writeContent("\n        ");
				if (selected != null) {
					jteOutput.writeContent("\n            <div style=\"padding-top: 40px\">\n                <span style=\"display: inline\">\n                    Ratio of\n                    <b>");
					jteOutput.setContext("b", null);
					jteOutput.writeUserContent(selected.get(0));
					jteOutput.writeContent("</b>\n                    to\n                    <b>");
					jteOutput.setContext("b", null);
					jteOutput.writeUserContent(selected.get(1));
					jteOutput.writeContent("</b>\n                    is\n                    <b>");
					jteOutput.setContext("b", null);
					jteOutput.writeUserContent(BigDecimal.valueOf(Double.parseDouble(selected.get(2))).round(new MathContext(3)));
					jteOutput.writeContent("</b>\n                </span>\n                <form action=\"/calculation/do/all?clear=do\" method=\"post\">\n                    <button type=\"submit\">Clear chosen acids</button>\n                </form>\n                <form action=\"/calculation/do/all\" method=\"post\" style=\"padding-top: 30px\">\n                    Count automatically\n                    <div>\n                        <input type=\"text\" name=\"weight\" placeholder=\"Enter weight\"");
					var __jte_html_attribute_2 = wR.getOrDefault("w", 15.0);
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_2);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n                        <input type=\"text\" name=\"ratio\" placeholder=\"Enter ratio\"");
					var __jte_html_attribute_3 = wR.getOrDefault("r", 1.5);
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_3);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n                        <button type=\"submit\">Do auto count</button>\n                    </div>\n                </form>\n            </div>\n        ");
				}
				jteOutput.writeContent("\n    </div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		HashMap<String, Double> weights = (HashMap<String, Double>)params.get("weights");
		Map<String, Double> acids = (Map<String, Double>)params.get("acids");
		LinkedList<String> selected = (LinkedList<String>)params.get("selected");
		Map<String, Double> wR = (Map<String, Double>)params.get("wR");
		HashMap<String, HashMap<String, Double>> oilsInfo = (HashMap<String, HashMap<String, Double>>)params.get("oilsInfo");
		render(jteOutput, jteHtmlInterceptor, weights, acids, selected, wR, oilsInfo);
	}
}
