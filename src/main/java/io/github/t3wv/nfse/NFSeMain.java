 package io.github.t3wv.nfse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.t3wv.nfse.nacional.WSFacade;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalCServ;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalDPS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalEnderNac;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalEndereco;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfDPS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfDPSTipoEmitente;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoIBSCBS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoIBSCBSIndDest;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoIBSCBSInfoTributos;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoIBSCBSInfoTributosGIBSCBS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoIBSCBSInfoValoresIBSCBS;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoPessoa;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoPrestador;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoTributacao;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalInfoValores;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalLocPrest;
import io.github.t3wv.nfse.nacional.classes.nfsenacional.NFSeSefinNacionalPostResponseSucesso;
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

    		//Files.write(Paths.get("/home/tecnosofti/Documents/nfse/nfse_cacerts.jks"), NFSeCadeiaCertificados.geraCadeiaCertificados(config));

    		final var nfseWebService = new WSFacade(config);

    		BigDecimal aliquota = nfseWebService.consultaAliquotaMunicipioServicoCompetencia("4205407", "010701");
    		logger.info("Aliquota: " + aliquota);

      		//String xml = nfseWebService.downloadNotaXml("31051032204884841000160000000000001426020564835559");
    		//logger.info("Xml: " + xml);

    		//byte[] pdf = nfseWebService.downloadNotaPdf("31051032204884841000160000000000001426020564835559");
    		//Files.write(Paths.get("/home/tecnosofti/Documents/nfse/nota.pdf"), pdf);
    		
    		NFSeSefinNacionalDPS dps = criarNFSeSefinNacionalDPSPreenchido();
    		NFSeSefinNacionalPostResponseSucesso responseSucesso = nfseWebService.emitirNFSe(dps);
    		
    		logger.info("chave: " + responseSucesso.getChaveAcesso());


        } catch (Exception e) {
            logger.error("Unexpected error in testNfse", e);
        }
    }

    /**
     * Cria e popula toda a hierarquia de objetos da classe {@link NFSeSefinNacionalDPS} com os
     * campos obrigatorios para emissao via web service da NFS-e Nacional (SEFIN).
     *
     * <p>Campos obrigatorios cobertos:
     * <ul>
     *   <li>infDPS: Id, tpAmb, dhEmi, verAplic, serie, nDPS, dCompet, tpEmit, cLocEmi</li>
     *   <li>prest: CNPJ, xNome, end (xLgr, nro, xBairro, endNac), regTrib (opSimpNac, regEspTrib)</li>
     *   <li>toma: CNPJ, xNome, end (xLgr, nro, xBairro, endNac)</li>
     *   <li>serv: locPrest (cLocPrestacao), cServ (cTribNac, xDescServ, cNBS)</li>
     *   <li>valores: vServPrest (vServ), trib (tribMun (tribISSQN, tpRetISSQN), totTrib)</li>
     * </ul>
     */
	public static NFSeSefinNacionalDPS criarNFSeSefinNacionalDPSPreenchido() {

		final String cnpjPrestador = "04884841000160";
		final String serieFormatada = "00901"; // serie padded para 5 digitos (uso no Id)
		final long   numeroDps     = 6L;
		final String cMunEmissao   = "4205407"; // Criciuma-SC

		// Id da DPS: "DPS" + cLocEmi(7) + tpInscFed(1=CPF|2=CNPJ|3=IM) + InscFed(14) + serie(5) + nDPS(15)
		// Ref. XSD: TSIdDPS — pattern DPS[0-9]{42}, length=45
		final String idDPS = String.format("DPS%s2%s%s%015d",
				cMunEmissao, cnpjPrestador, serieFormatada, numeroDps);

		// Endereco do prestador
		// XSD: TCEndereco -- xLgr, nro e xBairro sao obrigatorios; endNac ou endExt (choice obrigatorio)
		// XSD: TCEnderNac -- cMun e CEP sao obrigatorios
		NFSeSefinNacionalEndereco enderecoPrestador = new NFSeSefinNacionalEndereco()
				.setEnderecoNacional(new NFSeSefinNacionalEnderNac()
						.setCodigoMunicipio(cMunEmissao)
						.setCEP("88801000"))
				.setLogradouro("Avenida Centenario")
				.setNumero("2765")
				.setBairro("Centro");

		// ── Prestador ────────────────────────────────────────────────────────
		// XSD: TCInfoPrestador -- CNPJ/CPF/NIF (choice obrigatorio) + regTrib obrigatorio
		// setNome e setEndereco retornam void; constroem-se separadamente
		NFSeSefinNacionalInfoPrestador prestador = new NFSeSefinNacionalInfoPrestador()
				.setCNPJ(cnpjPrestador)
				.setRegimeTributario(new NFSeSefinNacionalRegTrib()
						.setOpSimplesNacional(NFSeSefinNacionalRegimeTributarioSituacaoSimplesNacional.NAO_OPTANTE)
						.setRegimeEspecialTributacao(NFSeSefinNacionalRegimeTributarioRegimeEspecialTributacao.NENHUM));
		prestador.setNome("EMPRESA TESTE LTDA");
		prestador.setEndereco(enderecoPrestador);

		// ── Tomador ──────────────────────────────────────────────────────────
		// XSD: TCInfoPessoa -- CNPJ/CPF/NIF (choice obrigatorio) + xNome obrigatorio
		// Tomador e opcional no infDPS (minOccurs="0"), mas normalmente exigido pelos municipios.
		NFSeSefinNacionalInfoPessoa tomador = new NFSeSefinNacionalInfoPessoa()
				.setCNPJ("99999999000191")
				.setNome("TOMADOR TESTE LTDA")
				.setEndereco(new NFSeSefinNacionalEndereco()
						.setEnderecoNacional(new NFSeSefinNacionalEnderNac()
								.setCodigoMunicipio("3550308") // Sao Paulo-SP
								.setCEP("01310100"))
						.setLogradouro("Avenida Paulista")
						.setNumero("1000")
						.setBairro("Bela Vista"));

		return new NFSeSefinNacionalDPS()
				.setInfDPS(new NFSeSefinNacionalInfDPS()
						// Id obrigatorio -- gerado conforme padrao TSIdDPS
						.setId(idDPS)
						.setTipoAmbiente(NFSeSefinNacionalTipoAmbiente.HOMOLOGACAO)
						.setDataHoraEmissao(ZonedDateTime.of(2025, 10, 23, 10, 33, 19, 0, ZoneId.of("-03:00")))
						.setVersaoApp("NFSe Fake Teste 1.0")
						.setSerie("901")
						.setNumeroDPS(numeroDps)
						.setDataInicioPrestacaoServico(LocalDate.of(2025, 10, 23))
						.setTipoEmitente(NFSeSefinNacionalInfDPSTipoEmitente.PRESTADOR)
						.setCodigoMunicipioEmissao(cMunEmissao)
						.setPrestador(prestador)
						.setTomador(tomador)
						// Servico
						// XSD: TCServ -- locPrest e cServ sao obrigatorios
						// XSD: TCCServ -- cTribNac (6 digitos), xDescServ e cNBS sao obrigatorios
						.setServicoPrestado(new NFSeSefinNacionalServ()
								.setLocalPrestacao(new NFSeSefinNacionalLocPrest()
										.setCodigoMunicipio(cMunEmissao))
								.setCServ(new NFSeSefinNacionalCServ()
										.setCodigoNacionalTributacaoISSQN("170601")
										.setDescricaoServico("PRESTACAO DE SERVICOS DE TECNOLOGIA DA INFORMACAO")
										.setCodigoNBS("114061100")))
						// ── Valores ──────────────────────────────────────────
						// XSD: TCInfoValores -- vServPrest e trib sao obrigatorios
						// XSD: TCInfoTributacao -- tribMun e totTrib sao obrigatorios
						// XSD: TCTribMunicipal -- tribISSQN e tpRetISSQN sao obrigatorios
						// XSD: TCTribTotal -- choice obrigatorio: vTotTrib | pTotTrib | indTotTrib | pTotTribSN
						.setValores(new NFSeSefinNacionalInfoValores()
								.setValoresServicoPrestado(new NFSeSefinNacionalVServPrest()
										.setValorServicos(BigDecimal.valueOf(0.01)))
								.setTributos(new NFSeSefinNacionalInfoTributacao()
										.setTributosMunicipais(new NFSeSefinNacionalTribMunicipal()
												.setTributacaoISSQN(NFSeSefinNacionalTribMunicipalTributacaoISSQN.OPERACAO_TRIBUTAVEL)
												.setTipoRetencaoISSQN(NFSeSefinNacionalTribMunicipalTipoRetencaoISSQN.NAO_RETIDO))
										.setTributosNacionais(new NFSeSefinNacionalTribFederal()
												.setPiscofins(new NFSeSefinNacionalTribOutrosPisCofins()
														.setCST(NFSeSefinNacionalTribOutrosPisCofinsSituacaoTributaria.CONTRIBUICAO_SEM_INCIDENCIA)))
										.setTotalTributos(new NFSeSefinNacionalTribTotal()
												.setIndicadorValorTotalTributos("0"))))
						// IBS/CBS
						// XSD: TIBSCBS -- cIndOp, indDest e valores sao obrigatorios
						// XSD: TGIBSCBSGrupo -- CST e cClassTrib sao obrigatorios
						// cIndOp: 6 digitos conforme AnexoVII-IndOp_IBSCBS
						// cClassTrib: 6 digitos conforme tabela de classificacao tributaria IBS/CBS


						.setInfoIBSCBS(new NFSeSefinNacionalInfoIBSCBS()
								.setIndDest(NFSeSefinNacionalInfoIBSCBSIndDest.TOMADOR)
								.setcIndOp("100301")
								.setValores(new NFSeSefinNacionalInfoIBSCBSInfoValoresIBSCBS()
										.setTrib(new NFSeSefinNacionalInfoIBSCBSInfoTributos()
												.setgIBSCBS(new NFSeSefinNacionalInfoIBSCBSInfoTributosGIBSCBS()
														.setCST("001")
														.setcClassTrib("000001"))))));
	}

}
