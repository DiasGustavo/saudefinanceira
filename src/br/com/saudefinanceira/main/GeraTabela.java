package br.com.saudefinanceira.main;

import br.com.saudefinanceira.util.*;
public class GeraTabela {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();

	}
}
