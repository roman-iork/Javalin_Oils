package gg.jte.generated.ondemand;
import transfer.OilTransfer;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JteoilGenerated {
	public static final String JTE_NAME = "oil.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,6,6,6,6,8,8,8,10,10,10,10,14,14,15,15,15,15,15,15,15,15,15,15,15,15,16,16,23,23,24,24,28,28,28,33,33,33,37,37,37,37,37,37,37,37,43,43,44,44,46,46,46,46,49,49,49,49,56,56,56,56,58,58,60,60,60,61,61,63,63,63,65,65,65,68,68,70,70,72,72,72,73,73,73,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<OilTransfer> data, List<String> acidsData) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div style=\"padding-left: 30px\" class=\"rounded-rectangle\">\n        <h3 style=\"display: inline-block; padding: 10px\">");
				jteOutput.setContext("h3", null);
				jteOutput.writeUserContent(data.get(0).getOilName());
				jteOutput.writeContent("</h3>\n\n        <form style=\"display: inline; padding-left: 150px\" action=\"/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(data.get(0).getOilName());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/acids\" method=\"post\" class=\"rightish\">\n\n            <label for=\"options\">acid</label>\n            <select id=\"options\" name=\"acid_name\">\n                ");
				for (var acidName : acidsData) {
					jteOutput.writeContent("\n                    <option");
					var __jte_html_attribute_0 = acidName;
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("option", "value");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("option", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("option", null);
					jteOutput.writeUserContent(acidName);
					jteOutput.writeContent("</option>\n                ");
				}
				jteOutput.writeContent("\n            </select>\n\n            <label style=\"padding-left: 20px\">acid %</label>\n            <input type=\"text\" name=\"acid_amount\" placeholder=\"Enter acid amount\">\n            <button type=\"submit\">Add</button>\n        </form>\n        ");
				for (var oilData : data) {
					jteOutput.writeContent("\n            ");
					if (oilData.getAcidName() != null) {
						jteOutput.writeContent("\n                <span style=\"display: flex\">\n                    <span class=\"rightish\">\n                        acid:\n                        <span class=\"leftish\">");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(oilData.getAcidName());
						jteOutput.writeContent("</span>\n                    </span>\n                    <span class=\"rightish\" style=\"position: absolute; left: 30%\">\n                        acid %:\n                        <span class=\"leftish\">\n                            ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(oilData.getAcidAmount());
						jteOutput.writeContent("\n                        </span>\n                    </span>\n                    <div style=\"position: absolute; left: 50%\">\n                        <form action=\"/delete/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(data.get(0).getOilName());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(oilData.getAcidName());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" method=\"post\" style=\"display: inline; padding-left: 60px\">\n                            <label>Delete acid</label>\n                            <button type=\"submit\">Delete</button>\n                        </form>\n                    </div>\n                </span>\n            ");
					}
					jteOutput.writeContent("\n        ");
				}
				jteOutput.writeContent("\n    </div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n     <div class=\"rounded-rectangle-second\">\n         <div style=\"padding-left: 30px; padding-top: 20px\">\n              <form style=\"display: inline; padding-left: 30px\" action=\"/acids?oil=");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(data.get(0).getOilName());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("\" method=\"post\" class=\"rightish\">\n                  <label>Create acid:</label>\n                  <input type=\"text\" name=\"acid_name\" placeholder=\"Enter acid name\">\n                  <button type=\"submit\">Create</button>\n              </form>\n         </div>\n     </div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div>\n        ");
				if (data.get(0).getAcidAmount() != 0) {
					jteOutput.writeContent("\n            <div class=\"rounded-rectangle-third\" style=\"padding-left: 30px\">\n                <h2>");
					jteOutput.setContext("h2", null);
					jteOutput.writeUserContent(data.get(0).getOilName());
					jteOutput.writeContent("</h2>\n                ");
					for (var oilData : data) {
						jteOutput.writeContent("\n                    <span style=\"display: flex\">\n                        ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(oilData.getAcidName());
						jteOutput.writeContent("\n                        <span style=\"position: absolute; left: 50%\">\n                            ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(oilData.getAcidAmount());
						jteOutput.writeContent("\n                        </span>\n                    </span>\n                ");
					}
					jteOutput.writeContent("\n            </div>\n        ");
				}
				jteOutput.writeContent("\n    </div>\n");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<OilTransfer> data = (List<OilTransfer>)params.get("data");
		List<String> acidsData = (List<String>)params.get("acidsData");
		render(jteOutput, jteHtmlInterceptor, data, acidsData);
	}
}
