package it.interno.anpr.security.message;

import it.interno.anpr.config.ParamHandler;
import it.interno.anpr.ws._3000anprservice.AnprPortType3000;
import it.interno.anpr.ws._3000anprservice.AnprService3000;
import it.interno.anpr.ws._3000consultazione.Richiesta3002;
import it.interno.anpr.ws._3000consultazione.Risposta3002;
import it.interno.anpr.ws._P000anprservice.AnprPortTypeP000;
import it.interno.anpr.ws._P000anprservice.AnprServiceP000;
import it.interno.anpr.ws._P000sc.RichiestaP001;
import it.interno.anpr.ws._P000sc.RispostaP001;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;

/** Gestore per l'invocazione del servizio Test Connessione
 * @author gferraro
 *
 */
public class OpP001Handler extends RequestHandler implements IMessageHandler<RispostaP001>  {
	private static Log LOGGER = LogFactory.getLog(OpP001Handler.class);

	public OpP001Handler(ParamHandler param) {
		super(param);
	}

	/* (non-Javadoc)
	 * @see it.sogei.security.message.IMessageHandler#sendRequest()
	 */
	public RispostaP001 sendRequest(){
		AnprServiceP000 port = new AnprServiceP000();

		try {
			AnprPortTypeP000 wsAnprPort = port.getAnprServicePortP000();

			setClient(wsAnprPort);

			InputStream xmlSource = this.getInputPayload();
			RichiestaP001 request = (RichiestaP001) jaxbXMLToObject(xmlSource, RichiestaP001.class);
			
			LOGGER.info("WSP001 invoke...");
			return wsAnprPort.elenco(request);

		} catch (Exception e) {
			LOGGER.error("Exception nell'invio richiesta", e);
			throw new RuntimeException(e);
		}
	}

	public boolean isResponseValid(RispostaP001 resp) {
		return ((resp.getRispostaOKP001() != null) || (resp.getRispostaKO() != null));
	}
}
