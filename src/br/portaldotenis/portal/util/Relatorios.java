package br.portaldotenis.portal.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.jasperreports.Report;

import com.sun.security.ntlm.Client;

public class Relatorios implements Report {

    private final List<Client> data;
    private Map<String, Object> parameters;

    public Relatorios(List<Client> data, Map<String, Object> parameters ) {
        this.data = data;
        this.parameters = new HashMap<String, Object>();
    }

    public Report addParameter(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public Collection<Client> getData() {
        return data;
    }

    public String getFileName() {
        return "report" + System.currentTimeMillis();
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public String getTemplate() {
        return "/"+Utils.CONTEXTO+"/torneio/teste.jasper";
    }

    public boolean isCacheable() {
        return true;
    }

}