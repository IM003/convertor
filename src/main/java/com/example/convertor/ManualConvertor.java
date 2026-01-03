package com.example.convertor;

public class ManualConvertor{

    //Convertir xml vers Json
    public static String xmlToJson(String texteXML){
        texteXML = texteXML.trim();
        return convertToJson(texteXML);
    }

    private static String convertToJson(String texteXML) {
        StringBuilder resultatJson = new StringBuilder();

        resultatJson.append("{");

        while (!texteXML.isEmpty()) {
            //on verifie s'il ya des tags sinon on arrete al boucle
            int openTagStart = texteXML.indexOf("<");
            int openTagEnd = texteXML.indexOf(">");
            if (openTagStart == -1 || openTagEnd == -1) break;

            String tag = texteXML.substring(openTagStart + 1, openTagEnd).trim();
            int closeTagStart = texteXML.indexOf("</" + tag + ">");
            if (closeTagStart == -1) break;

            //On remplace les tags par les : en ajoutant le contenu xml à texteJson
            String contenu = texteXML.substring(openTagEnd + 1, closeTagStart).trim();
            resultatJson.append("\"").append(tag).append("\":");

            if (contenu.contains("<")) {
                resultatJson.append(convertToJson(contenu));
            } else {
                resultatJson.append("\"").append(contenu).append("\"");
            }

            texteXML = texteXML.substring(closeTagStart + tag.length() + 3).trim();

            if (texteXML.startsWith("<")) {
                resultatJson.append(",");
            }

        }

        resultatJson.append("}");
        return resultatJson.toString();

    }

    //Convertir Json vers XML
    public static String jsonToXml(String texteJSON) {
        texteJSON = texteJSON.trim();

        if (texteJSON.startsWith("{")) {
            texteJSON = texteJSON.substring(1, texteJSON.length() - 1);
        }

        return convertToXml(texteJSON);
    }

    private static String convertToXml(String texteJson) {
        StringBuilder resultatXml = new StringBuilder();
        int i = 0;

        while (i < texteJson.length()) {

            if (texteJson.charAt(i) == '"') {

                int keyEnd = texteJson.indexOf('"', i + 1);
                String key = texteJson.substring(i + 1, keyEnd);

                i = texteJson.indexOf(":", keyEnd) + 1;

                // 🔥 NOUVEAU : ignorer espaces et retours ligne
                while (i < texteJson.length() &&
                        (texteJson.charAt(i) == ' ' ||
                                texteJson.charAt(i) == '\n')) {
                    i++;
                }

                resultatXml.append("<").append(key).append(">");

                if (texteJson.charAt(i) == '{') {
                    int compteur = 1;
                    int start = ++i;

                    while (compteur > 0) {
                        if (texteJson.charAt(i) == '{') compteur++;
                        if (texteJson.charAt(i) == '}') compteur--;
                        i++;
                    }

                    resultatXml.append(
                            convertToXml(texteJson.substring(start, i - 1))
                    );

                } else {
                    int valueStart = texteJson.indexOf('"', i) + 1;
                    int valueEnd = texteJson.indexOf('"', valueStart);
                    resultatXml.append(texteJson.substring(valueStart, valueEnd));
                    i = valueEnd + 1;
                }

                resultatXml.append("</").append(key).append(">");
            }

            i++;
        }

        return resultatXml.toString();
    }


}