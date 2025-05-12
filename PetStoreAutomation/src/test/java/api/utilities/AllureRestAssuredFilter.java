package api.utilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureRestAssuredFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		ByteArrayOutputStream logStream = new ByteArrayOutputStream();
		PrintStream logPrintStream = new PrintStream(logStream);

		// Log request
		logPrintStream.println("====== Request ======");
		logPrintStream.println(requestSpec.getMethod() + " " + requestSpec.getURI());
		requestSpec.getHeaders().asList()
				.forEach(header -> logPrintStream.println(header.getName() + ": " + header.getValue()));
		if (requestSpec.getBody() != null) {
			logPrintStream.println("Body: " + requestSpec.getBody());
		}

		// Execute request and get response
		Response response = ctx.next(requestSpec, responseSpec);

		// Log response
		logPrintStream.println("\n====== Response ======");
		logPrintStream.println("Status Code: " + response.getStatusCode());
		response.getHeaders().asList()
				.forEach(header -> logPrintStream.println(header.getName() + ": " + header.getValue()));
		logPrintStream.println("Body: " + response.getBody().asPrettyString());

		// Attach log to Allure
		Allure.addAttachment("API Request & Response", "text/plain", logStream.toString(), ".txt");

		return response;

	}

}
