package gg.jte.generated.ondemand;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JtelayoutGenerated {
	public static final String JTE_NAME = "layout.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,19,19,25,36,41,42,43,49,60,71,82,93,104,115,152,152,152,153,153,153,154,154,154,160,160,160,2,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content content2, Content content3) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Oils site</title>\n    <style>\n        body {\n            margin: 0;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            height: 100vh;\n            background-color: #a5806e; ");
		jteOutput.writeContent("\n        }\n\n        .container {\n            width: 95%;\n            height: 95%;\n            background-color: #a5806e; ");
		jteOutput.writeContent("\n            position: relative;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            flex-direction: column;\n        }\n\n        .horizontal-bar {\n            width: 100%;\n            height: 8%;\n            background-color: #d5a100; ");
		jteOutput.writeContent("\n            position: absolute;\n            left: 0%;\n            top: 5%;\n            display: flex;\n            align-items: center; ");
		jteOutput.writeContent("\n            justify-content: flex-start; ");
		jteOutput.writeContent("\n            padding-left: 20px; ");
		jteOutput.writeContent("\n        }\n\n        .rounded-rectangle {\n            width: 70%;\n            height: 35%;\n            background-color: #c6c3a5; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            left: 0%;\n            top: 40%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-second {\n            width: 25%;\n            height: 20%;\n            background-color: #a0b7ba; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            right: 0%;\n            top: 35%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-third {\n            width: 25%;\n            height: 35%;\n            background-color: #ccc7bc; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            left: 0%;\n            top: 80%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-for-oils {\n            width: 60%;\n            height: 80%;\n            background-color: #ccc7bc; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            left: 0%;\n            top: 55%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-for-calc {\n            width: 25%;\n            height: 80%;\n            background-color: #a0b7ba; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            right: 5%;\n            top: 55%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-result-left {\n            width: 75%;\n            height: 80%;\n            background-color: #c9c899; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            left: 0%;\n            top: 55%;\n            transform: translateY(-50%);\n        }\n\n        .rounded-rectangle-result-right {\n            width: 18%;\n            height: 80%;\n            background-color: #b2bfaa; ");
		jteOutput.writeContent("\n            border-radius: 15px;\n            position: absolute;\n            right: 0%;\n            top: 55%;\n            transform: translateY(-50%);\n        }\n\n        .rightish {\n            padding-right: 40px;\n        }\n\n        .leftish {\n                    padding-left: 5px;\n        }\n    </style>\n</head>\n\n<body>\n    <div class=\"container\">\n        <div class=\"horizontal-bar\">\n            <form action=\"/oil\" method=\"post\" class=\"rightish\">\n                <label>Find oil:</label>\n                <input type=\"text\" name=\"oil_name\" placeholder=\"Enter oil name\">\n                <button type=\"submit\">Find</button>\n            </form>\n            <form action=\"/oils\" method=\"post\" class=\"rightish\">\n                <label>Add oil:</label>\n                <input type=\"text\" name=\"oil_name\" placeholder=\"Enter oil name\">\n                <button type=\"submit\">Add</button>\n            </form>\n            <form action=\"/oils\" method=\"get\">\n                <label>See all oils:</label>\n                <button type=\"submit\">See</button>\n            </form>\n        </div>\n        <div style=\"display: inline-block\">\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content2);
		jteOutput.writeContent("\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content3);
		jteOutput.writeContent("\n        </div>\n    </div>\n</body>\n\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		Content content2 = (Content)params.get("content2");
		Content content3 = (Content)params.get("content3");
		render(jteOutput, jteHtmlInterceptor, content, content2, content3);
	}
}
