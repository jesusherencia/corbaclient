/**
*
* @author MARIE ANGE
*/
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ConnexOpenErp.AchatVente;
import ConnexOpenErp.AchatVenteHelper;
import ConnexOpenErp.AchatVentePackage.Product;

public class StartClient {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
			ORB orb = ORB.init(args, props);
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			AchatVente addobj = (AchatVente) AchatVenteHelper.narrow(ncRef
					.resolve_str("AchatVente"));			
//			for (;;) {
				Product[] liste = addobj.listerProducts();
//			}
			for(Product p : liste){
				System.out.println("id : " + p.id);
				System.out.println("name : " + p.name);
				System.out.println("-----------------------------------");
			}
			
		} catch (Exception e) {
			System.out.println("Hello Client exception: " + e);
			e.printStackTrace();
		}

	}

}