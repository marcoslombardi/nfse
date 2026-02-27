package io.github.t3wv.nfse.utils;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.t3wv.nfse.NFSeAppConfig;
import io.github.t3wv.nfse.nacional.WSFacade;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalCServ;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalDPS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfDPS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfDPSTipoEmitente;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoPessoa;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoPrestador;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoTributacao;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoValores;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalLocPrest;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalRegTrib;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalRegimeTributarioRegimeEspecialTributacao;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalRegimeTributarioSituacaoSimplesNacional;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalServ;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTipoAmbiente;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribFederal;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribMunicipal;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribMunicipalTipoRetencaoISSQN;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribMunicipalTributacaoISSQN;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribOutrosPisCofins;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribOutrosPisCofinsSituacaoTributaria;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalTribTotal;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalVServPrest;

/**
 * @author Marcos Lombardi de Andrade
 */
public class NFSeMain {

    private static final Logger logger = LoggerFactory.getLogger(NFSeMain.class);

    public static void main(String[] args) {
    	
    	try {
    		
    		NFSeAppConfig config = new NFSeAppConfig();
    		
    		// Files.write(Paths.get("/tmp/nfse/nfse_cacerts.jks"), NFSeCadeiaCertificados.geraCadeiaCertificados(config));
    		
    		final var nfseWebService = new WSFacade(config);
    		
    		BigDecimal aliquota = nfseWebService.consultaAliquotaMunicipioServicoCompetencia("3105103", "010701");
    		
    		String xml = nfseWebService.downloadNotaXml("31046343563456346000160003453000001426020634563456");
    		
    		byte[] pdf = nfseWebService.downloadNotaPdf("31051045363456345634160000045600001426054764567559");
    		
    		Files.write(Paths.get("/tmp/nfse/nota.pdf"), pdf);
    		
    		logger.info("Aliquota: " + aliquota);
    		logger.info("Xml: " + xml);
            
    		
        } catch (Exception e) {
            logger.error("Unexpected error in testNfse", e);
        }
    }

    /**
     * Cria e popula toda a hierarquia de objetos da classe NFSeSefinNacionalDPS para testes.
     */
	public static NFSeSefinNacionalDPS criarNFSeSefinNacionalDPSPreenchido() {
		
		return new NFSeSefinNacionalDPS()
	                		.setInfDPS(new NFSeSefinNacionalInfDPS()
	                        .setTipoAmbiente(NFSeSefinNacionalTipoAmbiente.HOMOLOGACAO)
	                        .setDataHoraEmissao(ZonedDateTime.of(2025, 10, 23, 10, 33, 19, 0, ZoneId.of("-03:00")))
	                        .setVersaoApp("NFSe Fake Teste 1.0")
	                        .setSerie("901")
	                        .setNumeroDPS(6)
	                        .setDataInicioPrestacaoServico(LocalDate.of(2025, 10, 23))
	                        .setTipoEmitente(NFSeSefinNacionalInfDPSTipoEmitente.PRESTADOR)
	                        .setCodigoMunicipioEmissao("4205407")
	                        .setPrestador(new NFSeSefinNacionalInfoPrestador().setCNPJ("09879879879898").setRegimeTributario(new NFSeSefinNacionalRegTrib().setOpSimplesNacional(NFSeSefinNacionalRegimeTributarioSituacaoSimplesNacional.NAO_OPTANTE).setRegimeEspecialTributacao(NFSeSefinNacionalRegimeTributarioRegimeEspecialTributacao.NENHUM)))
	                        .setTomador(new NFSeSefinNacionalInfoPessoa().setCPF("").setNome(""))
	                        .setServicoPrestado(new NFSeSefinNacionalServ().setLocalPrestacao(new NFSeSefinNacionalLocPrest().setCodigoMunicipio("4205407")).setCServ(new NFSeSefinNacionalCServ().setCodigoNacionalTributacaoISSQN("170601").setDescricaoServico("Teste").setCodigoNBS("114061100")))
	                        .setValores(new NFSeSefinNacionalInfoValores().setValoresServicoPrestado(new NFSeSefinNacionalVServPrest().setValorServicos(BigDecimal.valueOf(0.01))).setTributos(new NFSeSefinNacionalInfoTributacao().setTributosMunicipais(new NFSeSefinNacionalTribMunicipal().setTributacaoISSQN(NFSeSefinNacionalTribMunicipalTributacaoISSQN.OPERACAO_TRIBUTAVEL).setTipoRetencaoISSQN(NFSeSefinNacionalTribMunicipalTipoRetencaoISSQN.NAO_RETIDO)).setTributosNacionais(new NFSeSefinNacionalTribFederal().setPiscofins(new NFSeSefinNacionalTribOutrosPisCofins().setCST(NFSeSefinNacionalTribOutrosPisCofinsSituacaoTributaria.CONTRIBUICAO_SEM_INCIDENCIA))).setTotalTributos(new NFSeSefinNacionalTribTotal().setIndicadorValorTotalTributos("0")))));
       
	}

}