public class Main{
  public static void main(String ...args){
		java.net.http.HttpClient.newHttpClient().send(java.net.http.HttpRequest.newBuilder()
				.uri(java.net.URI.create("https://api.telegram.org/bot1107986005:AAEejkxU0KofALESwToms-aVckREPWmHpgw/sendMessage?text=javaaa&chat_id=452549370"))
				.build(), java.net.http.HttpResponse.BodyHandlers.ofString());
  }
}
