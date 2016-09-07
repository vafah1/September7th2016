package localhost.employees.employeeCreate;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class EmployeeDelete {


	/**
	 * 
	 * Removes created employee via id #
	 * @author Vafa Husain
	 * @since 2016-09-07
	 *
	 */

	/**
	 * the URL of the API we want to connect to
	 */
	protected static String endpoint = "http://localhost:1337/employee/";
	/**
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {

			//employee ID
			System.out.println("Please enter ID # of employee you wish to delete:");
			String employeeID = sc.nextLine();




			//creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format("id=%s" ,
					URLEncoder.encode(employeeID, charset)
					);

			//creates a new URL out of the endpoint, returnType, and queryString
			URL googleDirections= new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("DELETE");

			//if we do not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code:" + connection.getResponseCode());
			}

			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			//loop of buffer line by line until it return null, meaning there are no more lines

			while(br.readLine() != null){
				//print each line to the screen
				System.out.println(br.readLine());
			}

			//close connection to API
			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}





