package gg.jte.generated.ondemand;
@SuppressWarnings("unchecked")
public final class JtewelcomeGenerated {
	public static final String JTE_NAME = "welcome.jte";
	public static final int[] JTE_LINE_INFO = {29,29,29,29,29,29,29,29,29,29,29,29};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"en\">\n    <head>\n        <meta charset=\"UTF-8\">\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n        <title>Oils site</title>\n        <link rel=\"stylesheet\" href=\"style.css\">\n    </head>\n    <body>\n        <div class=\"container\">\n            <div class=\"horizontal-bar\">\n                <form action=\"/oil\" method=\"post\" class=\"rightish\">\n                    <label>Find oil:</label>\n                    <input type=\"text\" name=\"oil_name\" placeholder=\"Enter oil name\">\n                    <button type=\"submit\">Find</button>\n                </form>\n                <form action=\"/oils\" method=\"post\" class=\"rightish\">\n                    <label>Add oil:</label>\n                    <input type=\"text\" name=\"oil_name\" placeholder=\"Enter oil name\">\n                    <button type=\"submit\">Add</button>\n                </form>\n                <form action=\"/oils\" method=\"get\">\n                    <label>See all oils:</label>\n                    <button type=\"submit\">See</button>\n                </form>\n            </div>\n        </div>\n    </body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
