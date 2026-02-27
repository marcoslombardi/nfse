package io.github.t3wv.nfse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.commons.lang3.StringUtils;

public class NFSeAppConfig implements NFSeConfig {

    private KeyStore keyStoreCertificado = null;
    private KeyStore keyStoreCadeia = null;
    
	@Override
	public String getCertificadoSenha() {
		return "";
	}

	@Override
	public String getCadeiaCertificadosSenha() {
		return "";
	}

    public KeyStore getKeyStoreCertificado() throws KeyStoreException {
        if (this.keyStoreCertificado == null && StringUtils.isNotBlank("/tmp/nfse/certificado.pfx")) {
            this.keyStoreCertificado = KeyStore.getInstance("PKCS12");
            try (InputStream certificadoStream = new FileInputStream("/tmp/nfse/certificado.pfx")) {
                this.keyStoreCertificado.load(certificadoStream, this.getCertificadoSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possivel montar o KeyStore com a cadeia de certificados", e);
            }
        }
        return this.keyStoreCertificado;
    }

    public KeyStore getKeyStoreCadeia() throws KeyStoreException {
        if (this.keyStoreCadeia == null && StringUtils.isNotBlank("/tmp/nfse/cacerts.jks")) {
            this.keyStoreCadeia = KeyStore.getInstance("JKS");
            try (InputStream cadeia = new FileInputStream("/tmp/nfse/nfse_cacerts.jks")) {
                this.keyStoreCadeia.load(cadeia, this.getCadeiaCertificadosSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possivel montar o KeyStore com o certificado", e);
            }
        }
        return this.keyStoreCadeia;
    }

	@Override
	public boolean isTeste() {
		return false;
	}

}
