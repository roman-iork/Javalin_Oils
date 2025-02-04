package gg.jte.generated.ondemand;
import model.Oil;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JteoilsGenerated {
	public static final String JTE_NAME = "oils.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,6,6,6,6,9,9,11,11,11,11,11,11,11,12,12,12,12,16,16,17,17,17,17,21,21,23,23,25,25,25,25,26,26,30,30,32,32,32,33,33,33,33,37,37,46,46,47,47,47,47,47,47,48,48,48,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<String> data, List<String> calc) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div style=\"padding-left: 30px\" class=\"rounded-rectangle-for-oils\">\n        <h3>Oils:</h3>\n        ");
				for (var oil : data) {
					jteOutput.writeContent("\n            <span style=\"display: flex\">\n                <a href=\"/oils/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(oil);
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\" style=\"padding-left: 20px\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(oil);
					jteOutput.writeContent("</a>\n                <form style=\"display: inline; position: absolute; left: 40%\" action=\"/delete/");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(oil);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\" method=\"post\" class=\"rightish\">\n                    <label for=\"options\">delete oil</label>\n                    <button type=\"submit\">Delete</button>\n                </form>\n                ");
					if (!calc.contains(oil)) {
						jteOutput.writeContent("\n                    <form style=\"display: inline; position: absolute; left: 70%\" action=\"/calculation/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(oil);
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" method=\"post\">\n                        <label for=\"options\">add to calculation</label>\n                        <button type=\"submit\">Add</button>\n                    </form>\n                ");
					}
					jteOutput.writeContent("\n            </span>\n        ");
				}
				jteOutput.writeContent("\n    </div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				if (!calc.isEmpty()) {
					jteOutput.writeContent("\n        <div class=\"rounded-rectangle-for-calc\">\n            <div style=\"padding-left: 30px\">\n                <h3>Oils for calculation</h3>\n                ");
					for (var oilCalc : calc) {
						jteOutput.writeContent("\n                    <span style=\"display: flex\">\n                        ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(oilCalc);
						jteOutput.writeContent("\n                        <form style=\"display: inline; position: absolute; right: 10%\" action=\"/calculation/remove/one/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(oilCalc);
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" method=\"post\" class=\"rightish\">\n                            <button type=\"submit\">Remove</button>\n                        </form>\n                    </span>\n                ");
					}
					jteOutput.writeContent("\n                <form style=\"display: flex; padding-top: 20px; position: absolute; right: 10%\" action=\"/calculation/remove/all\" method=\"post\" class=\"rightish\">\n                    <button type=\"submit\">Remove all</button>\n                </form>\n                <form style=\"display: flex; padding-top: 40px\" action=\"/calculation/do/all\" method=\"post\" class=\"rightish\">\n                    <button type=\"submit\">Calculate</button>\n                </form>\n            <div>\n        </div>\n    ");
				}
				jteOutput.writeContent("\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<String> data = (List<String>)params.get("data");
		List<String> calc = (List<String>)params.get("calc");
		render(jteOutput, jteHtmlInterceptor, data, calc);
	}
}
