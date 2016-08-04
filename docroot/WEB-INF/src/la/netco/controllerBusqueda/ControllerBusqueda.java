package la.netco.controllerBusqueda;


import java.sql.ResultSet;
import java.sql.SQLException;

import la.netco.persistence.PersistenceUtil;


public class ControllerBusqueda {
	static String sqlexc="";
	public static ResultSet selectClsReg(){
		
		String sql ="select CRE_ID,CRE_NOM from CLASEREGISTRO  " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("no ahy clases de registro cargadas en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	
	public static ResultSet selectTipoPer(){
		System.out.println("Ingresando al metodo buscar Usuario");
		String sql ="select TRP_ID,TRP_NOM from TIPOREGISTROPERSONA  " ;
		try {
			
			ResultSet result;
			result=PersistenceUtil.realizaConsulta(sql);
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("no ahy clases de registro cargadas en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	public static void recurs(String rec,String rec2){
		String qry="SELECT PER_ID FROM PERSONA WHERE";
		String qry2="SELECT REG_ID FROM REGISTRO WHERE";
		String qry3="SELECT ETD_ID FROM ENTIDAD WHERE";
		sqlexc=rec2;
		
		String vec[]=rec.split(" ");
		
		int i=1;
		
		for(i=0;i<vec.length;i++){
			qry+=" contains(*,'"+vec[i]+"') ";
			qry2+=" contains(*,'"+vec[i]+"') ";
			qry3+=" contains(*,'"+vec[i]+"') ";
			
			if(i<vec.length-1){
				
				qry+="AND";
				qry2+="AND";
				qry3+="AND";
			}
		}	
		//qry para buscar en tabla Registros por titulo en campo REG_TOR ligado a personas naturales
				sqlexc+="SELECT top 1000 CRE_NOM, REG_TOR,REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES,REG_TOR,REG_OBS " +
								"FROM PERSONA " +
								"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
								"inner join REGISTRO on RPE_REG=REG_ID " +
								"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
								"where REG_TOR!='' AND REG_COD !=''  AND REG_ID in("+qry2+")" +
								" union  ";
		//qry para buscar en tabla Personas ligado a Registros
				sqlexc+="SELECT top 1000 CRE_NOM, REG_TOR,REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES,REG_TOR,REG_OBS " +
						"FROM PERSONA " +
						"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
						"inner join REGISTRO on RPE_REG=REG_ID " +
						"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
						"where REG_TOR!='' AND REG_COD!=''  AND PER_ID in("+qry+")" +
						" union  ";
		//qry para buscar en tabla Entidades ligado a Registros
				sqlexc+="SELECT top 1000 CRE_NOM, REG_TOR,REG_COD,REG_FEC ,REG_FCR,ETD_NOM , '-' ,'-',REG_DES,REG_TOR,REG_OBS " +
						"FROM ENTIDAD " +
						"inner join EXPEDIENTE on EXP_ETD = ETD_ID " +
						"inner join REGISTRO on REG_EXP=EXP_ID " +
						"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
						"where REG_TOR!='' AND REG_COD!=''  AND EXP_ETD in("+qry3+")  order by REG_FEC desc";
		
		
		
		/*//qry para buscar en tabla obras ligado a personas naturales
		sql+="SELECT top 1000 CRE_NOM, OBR_TIT,REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES,REG_TOR " +
						"FROM PERSONA] " +
						"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
						"inner join REGISTRO on RPE_REG=REG_ID " +
						"inner join OBRAS on REG_ID=OBR_REG " +
						"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
						"where OBR_ID in("+qry2+")" +
						" union  ";
		//qry para buscar en tabla Personas ligado a Obras
		sql+="SELECT top 1000 CRE_NOM, OBR_TIT,REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES,REG_TOR " +
				"FROM PERSONA " +
				"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
				"inner join REGISTRO on RPE_REG=REG_ID " +
				"inner join OBRAS on REG_ID=OBR_REG " +
				"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
				"where PER_ID in("+qry+")" +
				" union  ";
		//qry para buscar en tabla Entidades ligado a Obras
		sql+="SELECT top 1000 CRE_NOM, OBR_TIT,REG_COD,REG_FEC ,REG_FCR,ETD_NOM , '-' ,'-',REG_DES,REG_TOR " +
				"FROM ENTIDAD " +
				"inner join EXPEDIENTE on EXP_ETD = ETD_ID " +
				"inner join REGISTRO on REG_EXP=EXP_ID " +
				"inner join OBRAS on REG_ID=OBR_REG " +
				"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
				"where EXP_ETD in("+qry3+")";*/
		
			
		}
	
	public static void avanzada(String filtro,String otro){
		
		
		//qry para buscar en tabla Personas ligado a Registros
				sqlexc+="SELECT top 1000 CRE_NOM, REG_TOR,REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES,REG_TOR,REG_OBS " +
						"FROM PERSONA " +
						"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
						"inner join REGISTRO on RPE_REG=REG_ID " +
						"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
						"where REG_TOR!='' AND REG_COD!='' "+filtro.replace("ETD_NOM", "PER_NOM")+" "+otro+
						" union  ";
		//qry para buscar en tabla Entidades ligado a Registros
				sqlexc+="SELECT top 1000 CRE_NOM, REG_TOR,REG_COD,REG_FEC ,REG_FCR,ETD_NOM , '-' ,'-',REG_DES,REG_TOR,REG_OBS " +
						"FROM ENTIDAD " +
						"inner join EXPEDIENTE on EXP_ETD = ETD_ID " +
						"inner join REGISTRO on REG_EXP=EXP_ID " +
						"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
						"where REG_TOR!='' AND REG_COD!='' "+filtro.replace("PER_DOC","ETD_ID").replace("PER_NOM", "ETD_NOM").replace("PER_PAP", "ETD_NOM").replace("PER_SAP", "ETD_NOM")+
						"order by REG_FEC desc";	
		
			
		}
	
	
	public static ResultSet BusquedaSimple(String parametros,String filtro, String otro){
		sqlexc="";
		if(parametros!=null && !"".equals(parametros)){
			recurs(parametros,"");
		}else{
			avanzada(filtro, otro);			
		}
		System.out.println(sqlexc);
	
		try {
		
		ResultSet result;
		result=PersistenceUtil.realizaConsulta(sqlexc);
		if(result!=null){
		
		       return result;
		}else{
			
			System.out.println("no ahy coincidencias de busqueda");
			return result;
			
		}
		
		
	} catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Error de conexion a la bd  "+ex.getMessage());
	
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Error  excepcion  "+ex.getMessage());
		
	}
		return null;
		
	}
	
public static ResultSet Certificado(String nregistro){
		
		String sql ="SELECT CRE_NOM, REG_COD,REG_FEC ,REG_FCR,PER_NOM , PER_PAP ,PER_SAP,REG_DES, TRP_NOM,PER_DOC,PAI_NAC,PER_DIR,LUG_CIU,ACT_NOM " +
				"FROM PERSONA " +
				"inner join REGISTROPERSONA on RPE_PER = PER_ID " +
				"inner join REGISTRO on RPE_REG=REG_ID " +				
				"inner join CLASEREGISTRO on CRE_ID=REG_CRE " +
				"inner join TIPOREGISTROPERSONA on RPE_TRP=TRP_ID " +
				"left join LUGAR on PER_LUG=LUG_ID " +
				"inner join PAISES on PAI_ID=PER_NAC " +
				"inner join ACTIVIDAD on RPE_ACT=ACT_ID " +
				"where REG_COD='"+nregistro+"' order by RPE_TRP "  ;
		
		try {
		
		ResultSet result;
		result=PersistenceUtil.realizaConsulta(sql);
		if(result!=null){
		
		       return result;
		}else{
			
			System.out.println("no ahy coincidencias de busqueda");
			return result;
			
		}
		
		
	} catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Error de conexion a la bd  "+ex.getMessage());
	
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Error  excepcion  "+ex.getMessage());
		
	}
		return null;
		
	}
	public static ResultSet solicitanteqry(String nregistro){
		
		String sql ="SELECT ENT_NOM,ENT_PAP,ENT_SAP,ENT_NDO,PAI_NAC,ENT_TEL,ENT_CEL,ENT_DIR,LUG_CIU,MED_NOM,CAL_NOM, REG_ISBN,REG_NPG,REG_NED,REG_FPP,REG_TIR,REG_PAI,REG_DOB,REG_APF,REG_NUM,REG_CUA,ENT_ENT,TCO_NOM,ENT_NEN,PAI_NOM,USR_ABR " +
				"FROM ENTRADA " +
				"inner join EXPEDIENTECORRESPOND on ENT_ID = EXC_ENT " +
				"inner join EXPEDIENTE on EXC_EXP = EXP_ID " +
				"inner join REGISTRO on REG_EXP = EXP_ID " +
				"inner join PAISES on PAI_ID = ENT_NAC " +
				"inner join LUGAR on LUG_ID = ENT_LUG " +
				"inner join MEDIOSCORRESPONDENCIA on MED_ID = ENT_MED " +
				"inner join CALIDADREPRESENTANTE on CAL_ID = ENT_CAL " +
				"left join TIPOSCONTRATO on TCO_ID = REG_TCO "+
				"inner join USUARIO on ENT_USR=USR_ID "+
				"where REG_COD='"+nregistro+"'" ;
		
		try {
		
		ResultSet result;
		result=PersistenceUtil.realizaConsulta(sql);
		if(result!=null){
		
		       return result;
		}else{
			
			System.out.println("no ahy coincidencias de busqueda");
			return result;
			
		}
		
		
	} catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Error de conexion a la bd  "+ex.getMessage());
	
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Error  excepcion  "+ex.getMessage());
		
	}
		return null;
		
	}

	public static ResultSet fonogramasObrasAutor(String nregistro){
		
		String sql ="SELECT OBR_TIT,OBR_AUT " +
				"FROM OBRAS " +
				"inner join REGISTRO on REG_ID = OBR_REG " +			
				"where REG_COD='"+nregistro+"'" ;
		
		try {
		
		ResultSet result;
		result=PersistenceUtil.realizaConsulta(sql);
		if(result!=null){
		
		       return result;
		}else{
			
			System.out.println("no ahy coincidencias de busqueda");
			return result;
			
		}
		
		
	} catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Error de conexion a la bd  "+ex.getMessage());
	
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Error  excepcion  "+ex.getMessage());
		
	}
		return null;
		
	}
	public static ResultSet datosObras(String nregistro){
		
		String sql ="SELECT PRO_NOM,ELE_NOM " +
				"FROM ELEMENTO " +
				"inner join ELEMENTOREGISTRO on ELR_ELE = ELE_ID " +
				"inner join PROPIEDAD on PRO_ID = ELE_PRO " +
				"inner join REGISTRO on ELR_REG = REG_ID " +
				"where REG_COD='"+nregistro+"'" ;
		
		try {
		
		ResultSet result;
		result=PersistenceUtil.realizaConsulta(sql);
		if(result!=null){
		
		       return result;
		}else{
			
			System.out.println("no ahy coincidencias de busqueda");
			return result;
			
		}
		
		
	} catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Error de conexion a la bd  "+ex.getMessage());
	
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Error  excepcion  "+ex.getMessage());
		
	}
		return null;
		
	}

}
