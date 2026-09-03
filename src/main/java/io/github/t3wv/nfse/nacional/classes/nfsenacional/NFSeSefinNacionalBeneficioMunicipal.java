
package io.github.t3wv.nfse.nacional.classes.nfsenacional;

import org.simpleframework.xml.*;

@Root(name = "BeneficioMunicipal")
public class NFSeSefinNacionalBeneficioMunicipal {

    @Element(name = "nBM")
    protected String numeroBeneficio;
    @Element(name = "vRedBCBM", required = false)
    protected String valorReducaoBaseCalculoBeneficioMunicipal;
    @Element(name = "pRedBCBM", required = false)
    protected String percentualReducaoBaseCalculoBeneficioMunicipal;

    /**
     * Identificador do benefício municipal parametrizado pelo município.
     * @return numeroBeneficio
     */
    public String getNumeroBeneficio() {
        return numeroBeneficio;
    }

    /**
     * Identificador do benefício municipal parametrizado pelo município.
     *
     * @param numeroBeneficio numeroBeneficio
     */
    public NFSeSefinNacionalBeneficioMunicipal setNumeroBeneficio(String numeroBeneficio) {
        this.numeroBeneficio = numeroBeneficio;
        return this;
    }

    /**
     * Valor monetário informado pelo emitente para redução da base de cálculo (BC) do ISSQN devido a um Benefício Municipal (BM)
     * @return valorReducaoBaseCalculoBeneficioMunicipal
     */
    public String getValorReducaoBaseCalculoBeneficioMunicipal() {
        return valorReducaoBaseCalculoBeneficioMunicipal;
    }

    /**
     * Valor monetário informado pelo emitente para redução da base de cálculo (BC) do ISSQN devido a um Benefício Municipal (BM)
     *
     * @param valorReducaoBaseCalculoBeneficioMunicipal valorReducaoBaseCalculoBeneficioMunicipal
     */
    public NFSeSefinNacionalBeneficioMunicipal setValorReducaoBaseCalculoBeneficioMunicipal(String valorReducaoBaseCalculoBeneficioMunicipal) {
        this.valorReducaoBaseCalculoBeneficioMunicipal = valorReducaoBaseCalculoBeneficioMunicipal;
        return this;
    }

    /**
     * Valor percentual informado pelo emitente para redução da base de cálculo (BC) do ISSQN devido a um Benefício Municipal (BM)
     * @return percentualReducaoBaseCalculoBeneficioMunicipal
     */
    public String getPercentualReducaoBaseCalculoBeneficioMunicipal() {
        return percentualReducaoBaseCalculoBeneficioMunicipal;
    }

    /**
     * Valor percentual informado pelo emitente para redução da base de cálculo (BC) do ISSQN devido a um Benefício Municipal (BM)
     *
     * @param percentualReducaoBaseCalculoBeneficioMunicipal percentualReducaoBaseCalculoBeneficioMunicipal
     */
    public NFSeSefinNacionalBeneficioMunicipal setPercentualReducaoBaseCalculoBeneficioMunicipal(String percentualReducaoBaseCalculoBeneficioMunicipal) {
        this.percentualReducaoBaseCalculoBeneficioMunicipal = percentualReducaoBaseCalculoBeneficioMunicipal;
        return this;
    }

}
