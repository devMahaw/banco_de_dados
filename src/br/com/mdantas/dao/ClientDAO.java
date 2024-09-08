package br.com.mdantas.dao;

import br.com.mdantas.domain.Client;
import br.com.mdantas.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author marcelo.dantas
 */
public class ClientDAO implements IClientDAO {


    @Override
    public Integer register(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_CLIENTE (ID, CODE, NAME) VALUES (nextval('SQ_CLIENT'),?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCode());
            stm.setString(2, client.getName());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Client consult(String code) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Client client = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_CLIENTE WHERE CODE = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getLong("ID"));
                client.setCode(rs.getString("CODE"));
                client.setName(rs.getString("NAME"));
            }
            return client;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer delete(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_CLIENTE WHERE CODE = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCode());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer update(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE TB_CLIENTE SET CODE = ?, NAME = ? WHERE ID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCode());
            stm.setString(2, client.getName());
            stm.setLong(3, client.getId());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
