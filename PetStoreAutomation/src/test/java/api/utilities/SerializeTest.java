package api.utilities;

import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.apache.juneau.xml.XmlSerializer;

import api.payload.Product;

public class SerializeTest {

	public static void main(String[] args) throws SerializeException, ParseException {
		
		//serialize

		JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
		
		String sellerNames[] = {"AON Enterprise", "Gemeni pvt ltd","XYZ Software"};
		Product product = new Product("Mukesh", 30,"Blue");
		
		String json=jsonSerializer.serialize(product);
		System.out.println(json);
		
		XmlSerializer xmlSerializer = XmlSerializer.DEFAULT_NS_SQ_READABLE;
		System.out.println(xmlSerializer.serialize(product));
		
		HtmlSerializer htmlSerializer  = HtmlSerializer.DEFAULT_SQ_READABLE;
		System.out.println(htmlSerializer.serialize(product));
		
		//deserailize
		
		JsonParser jsonParser = JsonParser.DEFAULT;
		String jsonVal = "{\n"
				+ "	\"color\": \"Blue\",\n"
				+ "	\"name\": \"Mukesh\",\n"
				+ "	\"price\": 30\n"
				+ "}";
		Product pro = jsonParser.parse(jsonVal, Product.class);
		System.out.println(pro);
		
		
	}

}
