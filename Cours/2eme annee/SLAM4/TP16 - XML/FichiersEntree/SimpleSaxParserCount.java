import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.PrintStream;

public class SimpleSaxParserIndent
    extends DefaultHandler {

    private String currentElement = new String();
    private static String urlToParse;

    int compteur_indent = 0;

    static public void main(String[] args) 
    {
	try{
	// 
	// 1. Creation d'un JAXP SAXParserFactory et configuration
	// 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	//
	// si on veut valider le document par rapport a 
	// sa declaration de type
	//
	// factory.setValidating(true);

	//
	// 2. Creation d'un JAXP SAXParser
	//
	SAXParser saxParser = factory.newSAXParser();
	
	//
	// 3. Obtention du XMLReader
	//
	XMLReader xmlReader = saxParser.getXMLReader();
	
	//
	// 4. Affectation du ContentHandler pour le
	// XMLReader. Remarque: on utilise la classe courante
	//
	xmlReader.setContentHandler(new SimpleSaxParserIndent());
	
	//
	// 5. Affectation du ErrorHandler avant parsing
	//
	xmlReader.setErrorHandler(new MyErrorHandler(System.err));
	
	//
	// 6. On parse le document (en donnant son URL) en utilisant
	// le XMLReader
	//
	urlToParse = convertToFileURL("wg.xml");
	xmlReader.parse(urlToParse);
	} catch (Throwable t) {
	    t.printStackTrace();
        }
        System.exit(0);
    }

    // debut du document
    public void startDocument() 
	throws SAXException {
	System.out.println("Start Document: "+urlToParse);
    }

    // debut de l'element
    public void startElement(String namespaceURI, 
			     String localName, // local name
			     String rawName,   // qualified name
			     Attributes atts)
	throws SAXException
    {
	// recuperation du nom de l'element;

	String eltName = localName;
	if ("".equals(eltName)) eltName = rawName;  // namespaceAware = false
        String indent_String = "";

        for (int j = 0; j < compteur_indent; j++)
            indent_String += "\t";

        System.out.print(indent_String + "dbt element: "+ eltName+"\n");

        for (int i=0; i <atts.getLength(); i++) {
            // recuperation du nom de l'attribut et de
            // sa valeur
            String attName = atts.getQName(i);
            if ("".equals(attName)) attName = atts.getQName(i);
            System.out.print(indent_String + "\tattribut["+i+"]: "
                    + attName
                    + "=" + atts.getValue(i)+"\n");
        }
        compteur_indent++;

    }

    // Pour les noeuds textes
    public void characters (char[] ch, int start, int length)
    {
        String text = new String (ch, start, length);
        String indent_String = "";

        for (int j = 0; j < compteur_indent; j++)
            indent_String += "\t";
        System.out.print(indent_String + "\ttexte: --"+text+"--\n");
    }

    // d'apres vous
    public void endElement(String uri,
                           String localName,
                           String rawName)
	throws SAXException
    {
        compteur_indent--;

        String eltName = localName;
	if ("".equals(eltName)) eltName = rawName;
        String indent_String = "";

        for (int j = 0; j < compteur_indent; j++)
            indent_String += "\t";
        System.out.print(indent_String + "fin element: "+eltName+"\n");
    }

    // fin du document
    public void endDocument() 
	throws SAXException {
	System.out.print("End Document\n");
    }


    //////////////////////////////////////////////////
    /*  Convert from a filename to a file URL.      */
    //////////////////////////////////////////////////

    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    //////////////////////////////////////////////////
    /* classe interne pour reporter les erreurs     */
    //////////////////////////////////////////////////

    private static class MyErrorHandler implements ErrorHandler {
        /** Error handler output goes here */
        private PrintStream out;
        
        MyErrorHandler(PrintStream out) {
            this.out = out;
        }
        
        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }
            String info = "URI=" + systemId +
            " Line=" + spe.getLineNumber() +
            ": " + spe.getMessage();
            return info;
        }
        
        public void warning(SAXParseException spe) 
	    throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }
        
        public void error(SAXParseException spe) 
	    throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
        
        public void fatalError(SAXParseException spe) 
	    throws SAXException {
            String message = "Fatal Error: " 
		+ getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
