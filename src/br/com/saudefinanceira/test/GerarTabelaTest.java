package br.com.saudefinanceira.test;

import org.junit.Test;

import br.com.saudefinanceira.util.HibernateUtil;

public class GerarTabelaTest {
	
	@Test
	public void gerar(){
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
