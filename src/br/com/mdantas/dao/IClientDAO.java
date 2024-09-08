package br.com.mdantas.dao;

import br.com.mdantas.domain.Client;

/**
 * @author marcelo.dantas
 */
public interface IClientDAO {

    public Integer register(Client client) throws Exception;

    public Client consult(String code) throws Exception;

    public Integer delete(Client clientBD) throws Exception;

    public Integer update(Client clientBD) throws Exception;
}
