package de.cidaas.interceptor;

import java.util.HashMap;

public class TokenHelperTest {
	public static String getTokenWithRolesAndScopes() {
		// Scopes --> cidaas:userinfo , cidaas:write, cidaas:read, offline_access
		// Roles --> USER, BOSCH_ADMIN, BOSCH_SUPER_ADMIN, BOSCH_USER
		// Groups --> CIDAAS_ADMINS with roles ADMIN
		return "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB1ofBAalvKMhSa9QHD8s4E";
	}
	
	public static String getTokenWithoutRoles(){
		// Scopes --> Testscope
		// Roles --> none
		return "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZjOTY2MDI1LTU4MzctNDM0YS04YTBmLTkzOGJlMzBhMjRmZiJ9.eyJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJmMmI0Y2Q3Yy01ODdlLTRhMWQtOTRiOS0yOGM4OTE3N2JjZWIiLCJzdWIiOiJBTk9OWU1PVVMiLCJhdWQiOiIzMmJiOTYxYi02NmRmLTQ3OWUtOGYzYy01YjA1MDcyZWJmYjgiLCJpYXQiOjE1OTY1NDY5NDcsImF1dGhfdGltZSI6MTU5NjU0Njk0NywiaXNzIjoiaHR0cHM6Ly9uaWdodGx5YnVpbGQuY2lkYWFzLmRlIiwianRpIjoiODA2Y2JjNDUtMTI2Yy00YmRhLWJlODktMzAwMzY2MzE2ZGM5Iiwic2NvcGVzIjpbIlRlc3RzY29wZSJdLCJleHAiOjc5MDgwNjY5NDd9.hnPQvJ8QW-sOD2lQWXWBbptowebRVPtl2Qch5V1XOy23dijizywEvdXO76t1mCsgFKadE9VqNW0A3d0s2x95udIQJgbf9fyIcPTFXiJX7_FFbkFmAnmZ25jBcxeP0h8MBbgQcUHn1Kw4cFUkw2Ge7GEaG-2fKvOgt8Z_-8UdBn4";
	}
	
	public static String getExpiredToken() {
		return getTokenWithRolesAndScopes();
	}
	
	public static String getValidToken() {
		return getTokenWithoutRoles();
	}
	
	public static String getKId() {
		return "5ef7362c-883b-46d3-865a-e9bf31b06f6b";
	}
	
	public static String getClientId() {
		return "32bb961b-66df-479e-8f3c-5b05072ebfb8";
	}
	
	public static String getIssuer() {
		return "https://nightlybuild.cidaas.de";
	}
	
	public static HashMap<String, Object> getPublicKeyAsHashMap() {
		HashMap<String, Object> additionalAttributes = new HashMap<String, Object>();
		additionalAttributes.put("n", "1AncAdP9fO-rltEs4bE7djNeKW3RCbNBirdcxA2RPLLnG88uK9JmeVkZnL8A3J-Vj4mlV43506E3Asls3n9OSwuRpTnSxNMU2nXDCaK-mpFywHlgPk6eGcWvBBKZTGElp58rLfYxJJzG3j8Ug4af6kvG7CZhRthVuBNTFLf5JS0");
		additionalAttributes.put("e", "AQAB");
		return additionalAttributes;
	}
}
