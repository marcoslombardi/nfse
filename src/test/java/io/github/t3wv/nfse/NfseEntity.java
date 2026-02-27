package io.github.t3wv.nfse;

import io.github.t3wv.nfse.nacional.classes.nfsenacional.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Entidade que representa a NFS-e Nacional Brasileira.
 * Mapeada a partir de {@link NFSeSefinNacionalInfDPS} e suas classes relacionadas.
 */
public class NfseEntity {

    // ── Identificação da DPS ─────────────────────────────────────────────────
    /** ID da DPS: "DPS" + Cód.Mun(7) + TipoInscFed(1) + InscFed(14) + Serie(5) + NroDPS(15) */
    private String chaveAcesso;
    private String tipoAmbiente;
    private ZonedDateTime dataHoraEmissao;
    private String versaoApp;
    private String serie;
    private long numeroDps;
    /** Data de competência / início da prestação do serviço */
    private LocalDate dataInicioPrestacaoServico;
    private String tipoEmitente;
    private String codigoMunicipioEmissao;

    // ── Substituição ─────────────────────────────────────────────────────────
    private String substituicaoChaveNFSe;
    private String substituicaoMotivo;
    private String substituicaoObservacao;

    // ── Prestador ────────────────────────────────────────────────────────────
    private String prestadorCNPJ;
    private String prestadorCPF;
    private String prestadorNIF;
    private String prestadorCAEPF;
    private String prestadorInscricaoMunicipal;
    private String prestadorNome;
    private String prestadorTelefone;
    private String prestadorEmail;
    private String prestadorEndLogradouro;
    private String prestadorEndNumero;
    private String prestadorEndComplemento;
    private String prestadorEndBairro;
    private String prestadorEndCodigoMunicipio;
    private String prestadorEndCEP;
    // Regime de tributação
    private String prestadorRegimeSimplesNacional;
    private String prestadorRegimeEspecialTributacao;
    private String prestadorRegimeApuracaoAposLimiteSimples;

    // ── Tomador ──────────────────────────────────────────────────────────────
    private String tomadorCNPJ;
    private String tomadorCPF;
    private String tomadorNIF;
    private String tomadorCAEPF;
    private String tomadorInscricaoMunicipal;
    private String tomadorNome;
    private String tomadorTelefone;
    private String tomadorEmail;
    private String tomadorEndLogradouro;
    private String tomadorEndNumero;
    private String tomadorEndComplemento;
    private String tomadorEndBairro;
    private String tomadorEndCodigoMunicipio;
    private String tomadorEndCEP;

    // ── Intermediário ────────────────────────────────────────────────────────
    private String intermediarioCNPJ;
    private String intermediarioCPF;
    private String intermediarioNIF;
    private String intermediarioNome;
    private String intermediarioInscricaoMunicipal;
    private String intermediarioEmail;
    private String intermediarioTelefone;

    // ── Serviço ──────────────────────────────────────────────────────────────
    private String servicoCodigoMunicipioPrestacao;
    private String servicoCodigoPaisPrestacao;
    private String servicoCodigoNacionalTributacaoISSQN;
    private String servicoCodigoMunicipalTributacaoISSQN;
    private String servicoDescricao;
    private String servicoCodigoNBS;
    private String servicoCodigoInternoContribuinte;
    private String servicoInformacoesComplementares;

    // ── Valores ──────────────────────────────────────────────────────────────
    private BigDecimal valorServicos;
    private BigDecimal valorRecebidoIntermediario;
    private String valorDescontoIncondicionado;
    private String valorDescontoCondicionado;

    // ── Tributos Municipais (ISSQN) ───────────────────────────────────────────
    private String tribMunicipalTributacaoISSQN;
    private String tribMunicipalTipoRetencaoISSQN;
    private String tribMunicipalPercentualAliquota;
    private String tribMunicipalCodigoPais;
    private String tribMunicipalTipoImunidade;

    // ── Tributos Totais ───────────────────────────────────────────────────────
    private String tribTotalIndicador;
    private String tribTotalPercentualSimplesNacional;

    // ─────────────────────────────────────────────────────────────────────────

    public NfseEntity() {}

    /**
     * Constrói a entidade a partir do objeto {@link NFSeSefinNacionalInfDPS}.
     */
    public NfseEntity(NFSeSefinNacionalInfDPS dps) {

        // Identificação
        this.chaveAcesso                 = dps.getId();
        this.tipoAmbiente                = dps.getTipoAmbiente() != null ? dps.getTipoAmbiente().name() : null;
        this.dataHoraEmissao             = dps.getDataHoraEmissao();
        this.versaoApp                   = dps.getVersaoApp();
        this.serie                       = dps.getSerie();
        this.numeroDps                   = dps.getNumeroDPS();
        this.dataInicioPrestacaoServico  = dps.getDataInicioPrestacaoServico();
        this.tipoEmitente                = dps.getTipoEmitente() != null ? dps.getTipoEmitente().name() : null;
        this.codigoMunicipioEmissao      = dps.getCodigoMunicipioEmissao();

        // Substituição
        NFSeSefinNacionalSubstituicao subst = dps.getSubstituicaoNfse();
        if (subst != null) {
            this.substituicaoChaveNFSe  = subst.getChaveNFSe();
            this.substituicaoMotivo     = subst.getMotivo() != null ? subst.getMotivo().name() : null;
            this.substituicaoObservacao = subst.getMotivoObservacao();
        }

        // Prestador
        NFSeSefinNacionalInfoPrestador prest = dps.getPrestador();
        if (prest != null) {
            this.prestadorCNPJ               = prest.getCNPJ();
            this.prestadorCPF                = prest.getCPF();
            this.prestadorNIF                = prest.getNIF();
            this.prestadorCAEPF              = prest.getCAEPF();
            this.prestadorInscricaoMunicipal = prest.getInscricaoMunicipal();
            this.prestadorNome               = prest.getNome();
            this.prestadorTelefone           = prest.getTelefone();
            this.prestadorEmail              = prest.getEmail();
            NFSeSefinNacionalEndereco endPrest = prest.getEndereco();
            if (endPrest != null) {
                this.prestadorEndLogradouro  = endPrest.getLogradouro();
                this.prestadorEndNumero      = endPrest.getNumero();
                this.prestadorEndComplemento = endPrest.getComplemento();
                this.prestadorEndBairro      = endPrest.getBairro();
                if (endPrest.getEnderecoNacional() != null) {
                    this.prestadorEndCodigoMunicipio = endPrest.getEnderecoNacional().getCodigoMunicipio();
                    this.prestadorEndCEP             = endPrest.getEnderecoNacional().getCEP();
                }
            }
            NFSeSefinNacionalRegTrib reg = prest.getRegimeTributario();
            if (reg != null) {
                this.prestadorRegimeSimplesNacional            = reg.getOpSimplesNacional() != null ? reg.getOpSimplesNacional().name() : null;
                this.prestadorRegimeEspecialTributacao         = reg.getRegimeEspecialTributacao() != null ? reg.getRegimeEspecialTributacao().name() : null;
                this.prestadorRegimeApuracaoAposLimiteSimples  = reg.getRegimeApuracaoAposLimiteSimplesNacional() != null ? reg.getRegimeApuracaoAposLimiteSimplesNacional().name() : null;
            }
        }

        // Tomador
        NFSeSefinNacionalInfoPessoa toma = dps.getTomador();
        if (toma != null) {
            this.tomadorCNPJ               = toma.getCNPJ();
            this.tomadorCPF                = toma.getCPF();
            this.tomadorNIF                = toma.getNIF();
            this.tomadorCAEPF              = toma.getCAEPF();
            this.tomadorInscricaoMunicipal = toma.getInscricaoMunicipal();
            this.tomadorNome               = toma.getNome();
            this.tomadorTelefone           = toma.getTelefone();
            this.tomadorEmail              = toma.getEmail();
            NFSeSefinNacionalEndereco endToma = toma.getEndereco();
            if (endToma != null) {
                this.tomadorEndLogradouro  = endToma.getLogradouro();
                this.tomadorEndNumero      = endToma.getNumero();
                this.tomadorEndComplemento = endToma.getComplemento();
                this.tomadorEndBairro      = endToma.getBairro();
                if (endToma.getEnderecoNacional() != null) {
                    this.tomadorEndCodigoMunicipio = endToma.getEnderecoNacional().getCodigoMunicipio();
                    this.tomadorEndCEP             = endToma.getEnderecoNacional().getCEP();
                }
            }
        }

        // Intermediário
        NFSeSefinNacionalInfoPessoa interm = dps.getIntermediario();
        if (interm != null) {
            this.intermediarioCNPJ               = interm.getCNPJ();
            this.intermediarioCPF                = interm.getCPF();
            this.intermediarioNIF                = interm.getNIF();
            this.intermediarioNome               = interm.getNome();
            this.intermediarioInscricaoMunicipal = interm.getInscricaoMunicipal();
            this.intermediarioEmail              = interm.getEmail();
            this.intermediarioTelefone           = interm.getTelefone();
        }

        // Serviço
        NFSeSefinNacionalServ serv = dps.getServicoPrestado();
        if (serv != null) {
            if (serv.getLocalPrestacao() != null) {
                this.servicoCodigoMunicipioPrestacao = serv.getLocalPrestacao().getCodigoMunicipio();
                this.servicoCodigoPaisPrestacao      = serv.getLocalPrestacao().getCodigoPais();
            }
            if (serv.getCServ() != null) {
                this.servicoCodigoNacionalTributacaoISSQN   = serv.getCServ().getCodigoNacionalTributacaoISSQN();
                this.servicoCodigoMunicipalTributacaoISSQN  = serv.getCServ().getCodigoMunicipalTributacaoISSQN();
                this.servicoDescricao                       = serv.getCServ().getDescricaoServico();
                this.servicoCodigoNBS                       = serv.getCServ().getCodigoNBS();
                this.servicoCodigoInternoContribuinte       = serv.getCServ().getCodigoInternoContribuinte();
            }
            if (serv.getInformacoesComplementares() != null) {
                this.servicoInformacoesComplementares = serv.getInformacoesComplementares().getInformacoesComplementares();
            }
        }

        // Valores
        NFSeSefinNacionalInfoValores vals = dps.getValores();
        if (vals != null) {
            if (vals.getValoresServicoPrestado() != null) {
                this.valorServicos              = vals.getValoresServicoPrestado().getValorServicos();
                this.valorRecebidoIntermediario = vals.getValoresServicoPrestado().getValorRecebidoIntermediario();
            }
            if (vals.getValoresDescontosCondicionadosEIncondicionados() != null) {
                this.valorDescontoIncondicionado = vals.getValoresDescontosCondicionadosEIncondicionados().getValorDescontoIncondicionado();
                this.valorDescontoCondicionado   = vals.getValoresDescontosCondicionadosEIncondicionados().getValorDescontoCondicionado();
            }
            if (vals.getTributos() != null) {
                NFSeSefinNacionalTribMunicipal tribMun = vals.getTributos().getTributosMunicipais();
                if (tribMun != null) {
                    this.tribMunicipalTributacaoISSQN  = tribMun.getTributacaoISSQN() != null ? tribMun.getTributacaoISSQN().name() : null;
                    this.tribMunicipalTipoRetencaoISSQN = tribMun.getTipoRetencaoISSQN() != null ? tribMun.getTipoRetencaoISSQN().name() : null;
                    this.tribMunicipalPercentualAliquota = tribMun.getPercentualAliquota();
                    this.tribMunicipalCodigoPais         = tribMun.getCodigoPais();
                    this.tribMunicipalTipoImunidade      = tribMun.getTipoImunidade() != null ? tribMun.getTipoImunidade().name() : null;
                }
                NFSeSefinNacionalTribTotal totTrib = vals.getTributos().getTotalTributos();
                if (totTrib != null) {
                    this.tribTotalIndicador               = totTrib.getIndicadorValorTotalTributos();
                    this.tribTotalPercentualSimplesNacional = totTrib.getPercentualTotalSimplesNacional();
                }
            }
        }
    }

    // ── Getters e Setters ────────────────────────────────────────────────────

    public String getChaveAcesso() { return chaveAcesso; }
    public void setChaveAcesso(String chaveAcesso) { this.chaveAcesso = chaveAcesso; }

    public String getTipoAmbiente() { return tipoAmbiente; }
    public void setTipoAmbiente(String tipoAmbiente) { this.tipoAmbiente = tipoAmbiente; }

    public ZonedDateTime getDataHoraEmissao() { return dataHoraEmissao; }
    public void setDataHoraEmissao(ZonedDateTime dataHoraEmissao) { this.dataHoraEmissao = dataHoraEmissao; }

    public String getVersaoApp() { return versaoApp; }
    public void setVersaoApp(String versaoApp) { this.versaoApp = versaoApp; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public long getNumeroDps() { return numeroDps; }
    public void setNumeroDps(long numeroDps) { this.numeroDps = numeroDps; }

    public LocalDate getDataInicioPrestacaoServico() { return dataInicioPrestacaoServico; }
    public void setDataInicioPrestacaoServico(LocalDate dataInicioPrestacaoServico) { this.dataInicioPrestacaoServico = dataInicioPrestacaoServico; }

    public String getTipoEmitente() { return tipoEmitente; }
    public void setTipoEmitente(String tipoEmitente) { this.tipoEmitente = tipoEmitente; }

    public String getCodigoMunicipioEmissao() { return codigoMunicipioEmissao; }
    public void setCodigoMunicipioEmissao(String codigoMunicipioEmissao) { this.codigoMunicipioEmissao = codigoMunicipioEmissao; }

    public String getSubstituicaoChaveNFSe() { return substituicaoChaveNFSe; }
    public void setSubstituicaoChaveNFSe(String substituicaoChaveNFSe) { this.substituicaoChaveNFSe = substituicaoChaveNFSe; }

    public String getSubstituicaoMotivo() { return substituicaoMotivo; }
    public void setSubstituicaoMotivo(String substituicaoMotivo) { this.substituicaoMotivo = substituicaoMotivo; }

    public String getSubstituicaoObservacao() { return substituicaoObservacao; }
    public void setSubstituicaoObservacao(String substituicaoObservacao) { this.substituicaoObservacao = substituicaoObservacao; }

    public String getPrestadorCNPJ() { return prestadorCNPJ; }
    public void setPrestadorCNPJ(String prestadorCNPJ) { this.prestadorCNPJ = prestadorCNPJ; }

    public String getPrestadorCPF() { return prestadorCPF; }
    public void setPrestadorCPF(String prestadorCPF) { this.prestadorCPF = prestadorCPF; }

    public String getPrestadorNIF() { return prestadorNIF; }
    public void setPrestadorNIF(String prestadorNIF) { this.prestadorNIF = prestadorNIF; }

    public String getPrestadorCAEPF() { return prestadorCAEPF; }
    public void setPrestadorCAEPF(String prestadorCAEPF) { this.prestadorCAEPF = prestadorCAEPF; }

    public String getPrestadorInscricaoMunicipal() { return prestadorInscricaoMunicipal; }
    public void setPrestadorInscricaoMunicipal(String prestadorInscricaoMunicipal) { this.prestadorInscricaoMunicipal = prestadorInscricaoMunicipal; }

    public String getPrestadorNome() { return prestadorNome; }
    public void setPrestadorNome(String prestadorNome) { this.prestadorNome = prestadorNome; }

    public String getPrestadorTelefone() { return prestadorTelefone; }
    public void setPrestadorTelefone(String prestadorTelefone) { this.prestadorTelefone = prestadorTelefone; }

    public String getPrestadorEmail() { return prestadorEmail; }
    public void setPrestadorEmail(String prestadorEmail) { this.prestadorEmail = prestadorEmail; }

    public String getPrestadorEndLogradouro() { return prestadorEndLogradouro; }
    public void setPrestadorEndLogradouro(String prestadorEndLogradouro) { this.prestadorEndLogradouro = prestadorEndLogradouro; }

    public String getPrestadorEndNumero() { return prestadorEndNumero; }
    public void setPrestadorEndNumero(String prestadorEndNumero) { this.prestadorEndNumero = prestadorEndNumero; }

    public String getPrestadorEndComplemento() { return prestadorEndComplemento; }
    public void setPrestadorEndComplemento(String prestadorEndComplemento) { this.prestadorEndComplemento = prestadorEndComplemento; }

    public String getPrestadorEndBairro() { return prestadorEndBairro; }
    public void setPrestadorEndBairro(String prestadorEndBairro) { this.prestadorEndBairro = prestadorEndBairro; }

    public String getPrestadorEndCodigoMunicipio() { return prestadorEndCodigoMunicipio; }
    public void setPrestadorEndCodigoMunicipio(String prestadorEndCodigoMunicipio) { this.prestadorEndCodigoMunicipio = prestadorEndCodigoMunicipio; }

    public String getPrestadorEndCEP() { return prestadorEndCEP; }
    public void setPrestadorEndCEP(String prestadorEndCEP) { this.prestadorEndCEP = prestadorEndCEP; }

    public String getPrestadorRegimeSimplesNacional() { return prestadorRegimeSimplesNacional; }
    public void setPrestadorRegimeSimplesNacional(String prestadorRegimeSimplesNacional) { this.prestadorRegimeSimplesNacional = prestadorRegimeSimplesNacional; }

    public String getPrestadorRegimeEspecialTributacao() { return prestadorRegimeEspecialTributacao; }
    public void setPrestadorRegimeEspecialTributacao(String prestadorRegimeEspecialTributacao) { this.prestadorRegimeEspecialTributacao = prestadorRegimeEspecialTributacao; }

    public String getPrestadorRegimeApuracaoAposLimiteSimples() { return prestadorRegimeApuracaoAposLimiteSimples; }
    public void setPrestadorRegimeApuracaoAposLimiteSimples(String prestadorRegimeApuracaoAposLimiteSimples) { this.prestadorRegimeApuracaoAposLimiteSimples = prestadorRegimeApuracaoAposLimiteSimples; }

    public String getTomadorCNPJ() { return tomadorCNPJ; }
    public void setTomadorCNPJ(String tomadorCNPJ) { this.tomadorCNPJ = tomadorCNPJ; }

    public String getTomadorCPF() { return tomadorCPF; }
    public void setTomadorCPF(String tomadorCPF) { this.tomadorCPF = tomadorCPF; }

    public String getTomadorNIF() { return tomadorNIF; }
    public void setTomadorNIF(String tomadorNIF) { this.tomadorNIF = tomadorNIF; }

    public String getTomadorCAEPF() { return tomadorCAEPF; }
    public void setTomadorCAEPF(String tomadorCAEPF) { this.tomadorCAEPF = tomadorCAEPF; }

    public String getTomadorInscricaoMunicipal() { return tomadorInscricaoMunicipal; }
    public void setTomadorInscricaoMunicipal(String tomadorInscricaoMunicipal) { this.tomadorInscricaoMunicipal = tomadorInscricaoMunicipal; }

    public String getTomadorNome() { return tomadorNome; }
    public void setTomadorNome(String tomadorNome) { this.tomadorNome = tomadorNome; }

    public String getTomadorTelefone() { return tomadorTelefone; }
    public void setTomadorTelefone(String tomadorTelefone) { this.tomadorTelefone = tomadorTelefone; }

    public String getTomadorEmail() { return tomadorEmail; }
    public void setTomadorEmail(String tomadorEmail) { this.tomadorEmail = tomadorEmail; }

    public String getTomadorEndLogradouro() { return tomadorEndLogradouro; }
    public void setTomadorEndLogradouro(String tomadorEndLogradouro) { this.tomadorEndLogradouro = tomadorEndLogradouro; }

    public String getTomadorEndNumero() { return tomadorEndNumero; }
    public void setTomadorEndNumero(String tomadorEndNumero) { this.tomadorEndNumero = tomadorEndNumero; }

    public String getTomadorEndComplemento() { return tomadorEndComplemento; }
    public void setTomadorEndComplemento(String tomadorEndComplemento) { this.tomadorEndComplemento = tomadorEndComplemento; }

    public String getTomadorEndBairro() { return tomadorEndBairro; }
    public void setTomadorEndBairro(String tomadorEndBairro) { this.tomadorEndBairro = tomadorEndBairro; }

    public String getTomadorEndCodigoMunicipio() { return tomadorEndCodigoMunicipio; }
    public void setTomadorEndCodigoMunicipio(String tomadorEndCodigoMunicipio) { this.tomadorEndCodigoMunicipio = tomadorEndCodigoMunicipio; }

    public String getTomadorEndCEP() { return tomadorEndCEP; }
    public void setTomadorEndCEP(String tomadorEndCEP) { this.tomadorEndCEP = tomadorEndCEP; }

    public String getIntermediarioCNPJ() { return intermediarioCNPJ; }
    public void setIntermediarioCNPJ(String intermediarioCNPJ) { this.intermediarioCNPJ = intermediarioCNPJ; }

    public String getIntermediarioCPF() { return intermediarioCPF; }
    public void setIntermediarioCPF(String intermediarioCPF) { this.intermediarioCPF = intermediarioCPF; }

    public String getIntermediarioNIF() { return intermediarioNIF; }
    public void setIntermediarioNIF(String intermediarioNIF) { this.intermediarioNIF = intermediarioNIF; }

    public String getIntermediarioNome() { return intermediarioNome; }
    public void setIntermediarioNome(String intermediarioNome) { this.intermediarioNome = intermediarioNome; }

    public String getIntermediarioInscricaoMunicipal() { return intermediarioInscricaoMunicipal; }
    public void setIntermediarioInscricaoMunicipal(String intermediarioInscricaoMunicipal) { this.intermediarioInscricaoMunicipal = intermediarioInscricaoMunicipal; }

    public String getIntermediarioEmail() { return intermediarioEmail; }
    public void setIntermediarioEmail(String intermediarioEmail) { this.intermediarioEmail = intermediarioEmail; }

    public String getIntermediarioTelefone() { return intermediarioTelefone; }
    public void setIntermediarioTelefone(String intermediarioTelefone) { this.intermediarioTelefone = intermediarioTelefone; }

    public String getServicoCodigoMunicipioPrestacao() { return servicoCodigoMunicipioPrestacao; }
    public void setServicoCodigoMunicipioPrestacao(String servicoCodigoMunicipioPrestacao) { this.servicoCodigoMunicipioPrestacao = servicoCodigoMunicipioPrestacao; }

    public String getServicoCodigoPaisPrestacao() { return servicoCodigoPaisPrestacao; }
    public void setServicoCodigoPaisPrestacao(String servicoCodigoPaisPrestacao) { this.servicoCodigoPaisPrestacao = servicoCodigoPaisPrestacao; }

    public String getServicoCodigoNacionalTributacaoISSQN() { return servicoCodigoNacionalTributacaoISSQN; }
    public void setServicoCodigoNacionalTributacaoISSQN(String servicoCodigoNacionalTributacaoISSQN) { this.servicoCodigoNacionalTributacaoISSQN = servicoCodigoNacionalTributacaoISSQN; }

    public String getServicoCodigoMunicipalTributacaoISSQN() { return servicoCodigoMunicipalTributacaoISSQN; }
    public void setServicoCodigoMunicipalTributacaoISSQN(String servicoCodigoMunicipalTributacaoISSQN) { this.servicoCodigoMunicipalTributacaoISSQN = servicoCodigoMunicipalTributacaoISSQN; }

    public String getServicoDescricao() { return servicoDescricao; }
    public void setServicoDescricao(String servicoDescricao) { this.servicoDescricao = servicoDescricao; }

    public String getServicoCodigoNBS() { return servicoCodigoNBS; }
    public void setServicoCodigoNBS(String servicoCodigoNBS) { this.servicoCodigoNBS = servicoCodigoNBS; }

    public String getServicoCodigoInternoContribuinte() { return servicoCodigoInternoContribuinte; }
    public void setServicoCodigoInternoContribuinte(String servicoCodigoInternoContribuinte) { this.servicoCodigoInternoContribuinte = servicoCodigoInternoContribuinte; }

    public String getServicoInformacoesComplementares() { return servicoInformacoesComplementares; }
    public void setServicoInformacoesComplementares(String servicoInformacoesComplementares) { this.servicoInformacoesComplementares = servicoInformacoesComplementares; }

    public BigDecimal getValorServicos() { return valorServicos; }
    public void setValorServicos(BigDecimal valorServicos) { this.valorServicos = valorServicos; }

    public BigDecimal getValorRecebidoIntermediario() { return valorRecebidoIntermediario; }
    public void setValorRecebidoIntermediario(BigDecimal valorRecebidoIntermediario) { this.valorRecebidoIntermediario = valorRecebidoIntermediario; }

    public String getValorDescontoIncondicionado() { return valorDescontoIncondicionado; }
    public void setValorDescontoIncondicionado(String valorDescontoIncondicionado) { this.valorDescontoIncondicionado = valorDescontoIncondicionado; }

    public String getValorDescontoCondicionado() { return valorDescontoCondicionado; }
    public void setValorDescontoCondicionado(String valorDescontoCondicionado) { this.valorDescontoCondicionado = valorDescontoCondicionado; }

    public String getTribMunicipalTributacaoISSQN() { return tribMunicipalTributacaoISSQN; }
    public void setTribMunicipalTributacaoISSQN(String tribMunicipalTributacaoISSQN) { this.tribMunicipalTributacaoISSQN = tribMunicipalTributacaoISSQN; }

    public String getTribMunicipalTipoRetencaoISSQN() { return tribMunicipalTipoRetencaoISSQN; }
    public void setTribMunicipalTipoRetencaoISSQN(String tribMunicipalTipoRetencaoISSQN) { this.tribMunicipalTipoRetencaoISSQN = tribMunicipalTipoRetencaoISSQN; }

    public String getTribMunicipalPercentualAliquota() { return tribMunicipalPercentualAliquota; }
    public void setTribMunicipalPercentualAliquota(String tribMunicipalPercentualAliquota) { this.tribMunicipalPercentualAliquota = tribMunicipalPercentualAliquota; }

    public String getTribMunicipalCodigoPais() { return tribMunicipalCodigoPais; }
    public void setTribMunicipalCodigoPais(String tribMunicipalCodigoPais) { this.tribMunicipalCodigoPais = tribMunicipalCodigoPais; }

    public String getTribMunicipalTipoImunidade() { return tribMunicipalTipoImunidade; }
    public void setTribMunicipalTipoImunidade(String tribMunicipalTipoImunidade) { this.tribMunicipalTipoImunidade = tribMunicipalTipoImunidade; }

    public String getTribTotalIndicador() { return tribTotalIndicador; }
    public void setTribTotalIndicador(String tribTotalIndicador) { this.tribTotalIndicador = tribTotalIndicador; }

    public String getTribTotalPercentualSimplesNacional() { return tribTotalPercentualSimplesNacional; }
    public void setTribTotalPercentualSimplesNacional(String tribTotalPercentualSimplesNacional) { this.tribTotalPercentualSimplesNacional = tribTotalPercentualSimplesNacional; }
}