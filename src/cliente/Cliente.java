package cliente;
import java.util.ArrayList;

import servidor.InterfaceServer;
import servidor.Servidor;
import java.rmi.RemoteException;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Cliente {
	private String nome;
	static InterfaceServer serverIf;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public void checkMsg(Servidor server,String remetente) throws RemoteException {
		server.getMenssagem(remetente,nome);
	}

	public void sendMsg(Servidor server,String destinatario,String msg) throws RemoteException {
		server.receberMensagem(nome, destinatario, msg);
	}
	/*
	public void sendMsg(Cliente remetente,String destinatario, String msg) throws RemoteException {
		serverIf.getMenssagem(destinatario, msg);
		}
	*/
	public void sendMsg(Servidor server,JSONObject jsonObject) throws RemoteException {
		serverIf.getMenssagem(server, jsonObject);
		}
		
		public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, JSONException {
			String hostName ="localhost";
			String serviceName="ChatSistemasDistribuidos";
			
			serverIf = ( InterfaceServer )Naming.lookup("rmi://" + hostName + "/" + serviceName);	
			JSONObject objJSON = new JSONObject();
			Cliente c1 = new Cliente("joao");
			Cliente c2 = new Cliente("maria");
			Servidor server = new Servidor();
			
			objJSON.put("maria","Ol�, tudo bem?");
			objJSON.put("maria","Ol�");
			
			//System.out.println(objJSON.toString());	
			System.out.println(objJSON.toString());	
			//c1.sendMsg(server,"maria", "ol�, tudo bem?");
			c1.sendMsg(server,objJSON.toString(), serviceName);
			c1.sendMsg(server,objJSON.toString(), serviceName);
			c1.checkMsg(server, "joao");
			
		}
	}

	
