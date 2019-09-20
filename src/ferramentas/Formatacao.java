package ferramentas;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author Jonas Dhein
 */
public class Formatacao {

    static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static void colocaMascara(JFormattedTextField campo, String mascara){
        try {
            MaskFormatter maskData;  
            maskData = new MaskFormatter(mascara);
            
            /*Repare na chamada ao setPlaceholerCharacter que diz ao componente para 
            colocar um UNDERLINE nos espaços de digitação enquanto o usuário 
            não preenche com a data desejada.*/
            maskData.setPlaceholderCharacter('_');
            maskData.setValueContainsLiteralCharacters(false);
            maskData.install(campo);
                
        } catch (ParseException ex) {
            System.out.println("Erro ao colocar máscara na data: " + ex.getMessage());
        }
    }
    
    public static JFormattedTextField getFormatado(String formato) {
        JFormattedTextField campoFormatado = null;
        MaskFormatter format = new MaskFormatter();
        
        try {
            format.setMask(formato);
            format.setPlaceholderCharacter(' ');
            format.setValueContainsLiteralCharacters(false);
            campoFormatado = new JFormattedTextField(format);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return campoFormatado;
    }
    
    public static void formatarDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

    public static JFormattedTextField getTelefone() {
        return getFormatado("(##) ####-####");
    }

    public static JFormattedTextField getCNPJ() {
        return getFormatado("##.###.###/####-##");
    }

    public static JFormattedTextField getCPF() {
        return getFormatado("###.###.###-##");
    }

    public static JFormattedTextField getData() {
        return getFormatado("##/##/####");
    }
    
    public static JFormattedTextField getDataHora() {
        return getFormatado("##/##/#### ##:##");
    }

    public void formatoDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }
    
    public static String retornaDataAtual(String formato){
        try{
            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat(formato);
            
            return formatador.format(data);
                        
        }catch(Exception ex){
            return "";
        }
    }

    public static void reformatarData(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##/##/####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCpf(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("###.###.###-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCnpj(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##.###.###/####-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarTelefone(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##)#####-####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String ajustaDataHoraDMA(String data) {
        String dataFormatada = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(dataAMD);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static String ajustaDataHoraAMD(String data) {
        String dataFormatada = null;
        try {
            Date dataDMA = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(data);
            dataFormatada = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(dataDMA);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }
    
    public static String soNumero(String dado){
        try{
            return dado.replaceAll("\\D+","");
            
        }catch(Exception ex){
            return "";
        }
    }
}
