package com.bonifici.rest.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bonifici.rest.component.Cliente;
import com.bonifici.rest.component.Conto;
import com.bonifici.rest.component.Transazione;
import com.bonifici.rest.service.BonificiService;

@RestController
@RequestMapping("/private")
public class BonificiController {
	
	private static final BigDecimal MAX_LIMIT= new BigDecimal(50000);
	
	@Autowired
	private BonificiService bonificoService;
	
	private static final Logger logger = LogManager.getLogger(BonificiController.class);
	
	//for response verify id_transaction
	private static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
	
	private static String getRequestId(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
	}
	
	private static String getCro(int n){
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
	}
	
	@CrossOrigin
    @RequestMapping(value="/cliente/{idcliente}/conto/{idconto}/bonifico/prepare", method=RequestMethod.GET, 
    	produces="application/json")
	@ResponseBody
    public String prepare(@PathVariable(name="idcliente") int idcliente, @PathVariable(name="idconto") int idconto) {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Calendar c=new GregorianCalendar();
        c.add(Calendar.DATE, 30);
        Date dateAfter30Days=c.getTime();
        JSONObject response = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("messages", new JSONArray());
        result.put("outcome", "SUCCESS");
        result.put("requestId", getRequestId(24));
        data.put("oggi", dt.format(today));
        data.put("dataLimite", dt.format(dateAfter30Days));
        response.put("result", result);
        response.put("data", data);
        logger.info("Fine prepare");
        return response.toString();
    }  
	
	@CrossOrigin
    @RequestMapping(value="/cliente/{idcliente}/conto/{idconto}/bonifico/verify", method=RequestMethod.POST, 
    		headers="Accept=application/json", produces="application/json")
	@ResponseBody
    public String verify(@RequestBody String jsonString, @PathVariable(name="idcliente") int idcliente, @PathVariable(name="idconto") int idconto) {
		Cliente cliente = bonificoService.getCliente(idcliente);
		Conto conto = bonificoService.getConto(idconto);
		try {
		     JSONObject jsonObject = new JSONObject(jsonString);
		     String divisa = jsonObject.getJSONObject("data").getJSONObject("importo").getString("divisa");
		     BigDecimal importo = new BigDecimal(jsonObject.getJSONObject("data").getJSONObject("importo")
		    		 .getString("ammontare").replace(',', '.'));
		     String dataEsecuzione = jsonObject.getJSONObject("data").getString("dataEsecuzione");
		     String nominativoBeneficiario = jsonObject.getJSONObject("data").getString("nominativoBeneficiario");
		     String ibanBeneficiario = jsonObject.getJSONObject("data").getString("ibanBeneficiario");
		     String causaleBonifico = jsonObject.getJSONObject("data").getString("causaleBonifico");
		     if (importo.compareTo(MAX_LIMIT) <= 0 && (importo.compareTo(conto.getSaldo()) <= 0)){
		    	 String idTransazione = generateString();
		    	 Transazione transazione = new Transazione();
		    	 transazione.setIdTransazione(idTransazione);
		    	 transazione.setDivisa(divisa);
		    	 transazione.setImporto(importo);
		    	 transazione.setDataEsecuzione(new SimpleDateFormat("dd/MM/yyyy").parse(dataEsecuzione));
		    	 transazione.setNominativoBeneficiario(nominativoBeneficiario);
		    	 transazione.setIbanBeneficiario(ibanBeneficiario);
		    	 transazione.setCausale(causaleBonifico);
		    	 transazione.setCommissione(conto.getCommissione());
		    	 transazione.setCliente(cliente);
		    	 transazione.setConto(conto);
		    	 bonificoService.createTransazione(transazione);
		    	 return generateVerifyResponse(importo, conto.getCommissione(), divisa, idTransazione);
		     }
		     if (importo.compareTo(MAX_LIMIT) > 0){
		    	 return generateErrorResponse("Limite massimo per il bonifico superato");
		     }
		     if (importo.compareTo(conto.getSaldo()) > 0){
		    	 return generateErrorResponse("Saldo non disponibile per eseguire il bonifico richiesto");
		     }
		}catch (JSONException ex){
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Json format incorrect!", ex);
		}catch (NumberFormatException ex){
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Number format exception!", ex);
		}
		catch(ParseException ex){
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Cannot parse the request!", ex);
		}
		return generateErrorResponse("Errore durante l'esecuzione del bonifico");
    }  
	
	
	@CrossOrigin
    @RequestMapping(value="/cliente/{idcliente}/conto/{idconto}/bonifico/{idtransazione}/execute", method=RequestMethod.POST, 
    		headers="Accept=application/json", produces="application/json")
	@ResponseBody
    public String execute(@PathVariable(name="idcliente") int idcliente, @PathVariable(name="idconto") int idconto
    		, @PathVariable(name="idtransazione") String idtransazione) {
		Conto conto = bonificoService.getConto(idconto);
		Transazione transazione = bonificoService.getTransazione(idtransazione);
		try {
		     conto.setSaldo(conto.getSaldo().subtract(transazione.getImporto().add(transazione.getCommissione())));
		     transazione.setCro(getCro(11));
		     transazione.setConto(conto);
		     bonificoService.executeTransazione(transazione,conto);
		     return generateExecuteResponse(transazione.getNominativoBeneficiario(), transazione.getCro());
		}catch (JSONException ex){
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Json format incorrect!", ex);
		}
    }  
	
	private String generateExecuteResponse(String nominativoBeneficiario, String cro){
		JSONObject response = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject resultMessage = new JSONObject();
        JSONObject summaryMessage = new JSONObject();
        resultMessage.put("message", "Hai effettuato un bonifico a " + nominativoBeneficiario);
        summaryMessage.put("message", "Una volta prodotta, troverai la contabile delle tue operazioni tra i documenti!");
        JSONArray messageArray = new JSONArray();
        messageArray.put(resultMessage);
        messageArray.put(summaryMessage);
        result.put("messages", messageArray);
        result.put("outcome", "SUCCESS");
        result.put("requestId", getRequestId(24));
        data.put("cro", cro);
        response.put("result", result);
        response.put("data", data);
        return response.toString();
	}
	
	private String generateVerifyResponse(BigDecimal importo, BigDecimal commissione, String divisa, String idTransazione){
		JSONObject response = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject totalAmount = new JSONObject();
        JSONObject commissions = new JSONObject();
        JSONObject transaction = new JSONObject();
        result.put("messages", new JSONArray());
        result.put("outcome", "SUCCESS");
        result.put("requestId", getRequestId(24));
        totalAmount.put("amount", importo);
        totalAmount.put("currency", divisa);
        commissions.put("commissions", commissione);
        commissions.put("currency", divisa);
        data.put("totalAmount", totalAmount);
        data.put("commissions", commissions);
        transaction.put("id", idTransazione);
        response.put("result", result);
        response.put("data", data);
        response.put("transaction", transaction);
        return response.toString();
	}
	
	private String generateErrorResponse(String message){
		JSONObject response = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject errorMessage = new JSONObject();
        errorMessage.put("message", message);
        result.put("messages", new JSONArray().put(errorMessage));
        result.put("outcome", "ERROR");
        response.put("result", result);
        return response.toString();
	}
	
	

}
