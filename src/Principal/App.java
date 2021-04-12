package Principal;

import Controlador.CtrlLogin;
import Vista.FrmLogin;

public class App {
    public static void main(String[] args) {
        FrmLogin fLogin = new FrmLogin();
        
        CtrlLogin cLogin = new CtrlLogin(fLogin);
        cLogin.inicializar();
    }
}
