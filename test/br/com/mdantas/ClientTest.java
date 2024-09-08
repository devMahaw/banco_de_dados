package br.com.mdantas;

import br.com.mdantas.dao.ClientDAO;
import br.com.mdantas.dao.IClientDAO;
import br.com.mdantas.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author marcelo.dantas
 */
public class ClientTest {

    private IClientDAO dao;
    private Client client;

    @Before
    public void setUp() throws Exception {
        dao = new ClientDAO();
        client = new Client();
        client.setCode("1");
        client.setName("Marcelo Dantas");
        dao.register(client);
    }

    @After
    public void tearDown() throws Exception {
        Client clientBD = dao.consult(client.getCode());
        if (clientBD != null) {
            dao.delete(clientBD);
        }
    }

    @Test
    public void registerAndUpdateTest() throws Exception {
        Client clientBD = dao.consult(client.getCode());
        assertNotNull(clientBD);
        assertNotNull(clientBD.getId());
        assertEquals(client.getCode(), clientBD.getCode());
        assertEquals(client.getName(), clientBD.getName());

        clientBD.setName("Marcelo Dantas Updated");
        Integer qtdUpdt = dao.update(clientBD);
        assertTrue(qtdUpdt == 1);

        Client updatedClientBD = dao.consult(clientBD.getCode());
        assertNotNull(updatedClientBD);
        assertEquals("Marcelo Dantas Updated", updatedClientBD.getName());
    }
}