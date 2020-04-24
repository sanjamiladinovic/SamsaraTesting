package resources;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider
	public Object[][] getData() {

		return new Object[][] {

				{ "6", "", "samasa", 's' }, { "7", "sas", "samasa", 's' }, { "8", "sasa", "samasa", 's' },

		};

	}

	@DataProvider
	public Object[][] getDataSearchKey() {

		return new Object[][] {

				{ "6", "sa", "samasa", 's' }, { "7", "sasa", "samasa", 's' }, { "8", "sasas", "samasa", 's' },

		};

	}

	@DataProvider()

	public Object[][] getDataUsernamePassword() {

		String data256 = "";
		String sanja = "sanja";
		for (int i = 0; i < 256 / sanja.length() + 1; i++) {

			data256 = data256 + "sanja";
		}
		System.out.println(data256.length());
		Object[][] data = new Object[13][2];
		data[0][0] = "user";
		data[0][1] = "password";

		data[1][0] = "user";
		data[1][1] = "password1";

		data[2][0] = "user1";
		data[2][1] = "password";

		data[3][0] = "";
		data[3][1] = "";

		data[4][0] = "";
		data[4][1] = "password";

		data[5][0] = "user";
		data[5][1] = "";

		data[6][0] = "00000000";
		data[6][1] = "password";

		data[7][0] = "user";
		data[7][1] = data256;

		data[8][0] = "trshryhjuryhrgrejdhajfdgnjdkgdlkgd/klgjdkhj/lsrkjglesgjkldgjm/klsjgklesrgj";
		data[8][1] = "password";

		data[9][0] = ".#@#";
		data[9][1] = "password";

		data[10][0] = "";
		data[10][1] = "password@";

		data[11][0] = "user@";
		data[11][1] = "";

		data[12][0] = "user@";
		data[12][1] = "password@";

		return data;
	}

	@DataProvider
	public Object[] getDataTestLoginCodeWarningMessage() {

		Object[] data1 = new Object[3];
		data1[0] = "";
		data1[1] = "55555";
		data1[2] = "jdgjgdj";
		return data1;
	}

	@DataProvider()
	public Object[][] getDataTestRegisterNewAccount() {

		Object[][] data = new Object[2][8];

		data[0][0] = "user87";
		data[0][1] = "Test Name";
		data[0][2] = "Test Last Name";
		data[0][3] = "Nothing";
		data[0][4] = "First teacher?";
		data[0][5] = "Secret1";
		data[0][6] = "User87";
		data[0][7] = "user87";

		data[1][0] = "";
		data[1][1] = "name88";
		data[1][2] = "lastname88";
		data[1][3] = "everything";
		data[1][4] = "Age?";
		data[1][5] = "99";
		data[1][6] = "Ggg88";
		data[1][7] = "Ggg88";

		return data;
	}
	@DataProvider()

	public Object[][] getDataTestResetPassword() {

		Object[][] data = new Object[2][4];
		data[0][0] = "user";
		data[0][1] = "bla bla";
		data[0][2] = "password";
		data[0][3] = "password";

		data[1][0] = "user";
		data[1][1] = "bla bla";
		data[1][2] = "Password1";
		data[1][3] = "Password1";
//		
//		data[2][0] = "sanja";
//		data[2][1] = "444";
//		data[2][2] = "Password2";
//		data[2][3] = "Password1";
//		
//		data[3][0] = "igor";
//		data[3][1] = "null";
//		data[3][2] = "Password3";
//		data[3][3] = "Password3";
//		
//		data[4][0] = "marko";
//		data[4][1] = "da da da";
//		data[4][2] = "";
//		data[4][3] = "";
		return data;
		
	}
	
	@DataProvider
	public Object[][] getDataVigenereCodeTest() {

		return new Object[][] {

				{ "6", "", "samasa", 's' }, 
				{ "7", "", "samasa", 's' }, 
				{ "8", "", "samasa", 's' },

		};

	}

}
