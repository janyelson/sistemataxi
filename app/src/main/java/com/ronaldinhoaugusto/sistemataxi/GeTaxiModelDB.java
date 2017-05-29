package com.ronaldinhoaugusto.sistemataxi;

import android.provider.BaseColumns;

/**
 * Created by JANYELSON on 22/05/2017.
 */

public class GeTaxiModelDB {

    private GeTaxiModelDB(){}

    public static abstract class MotoristaRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "motorista";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "nome";
        public static final String COLUMN_NAME_CNH = "cnh";
        public static final String COLUMN_NAME_CPF = "cpf";
        public static final String COLUMN_NAME_DATE_ADMISSION = "data_admissao";
        public static final String COLUMN_NAME_DATE_NASCIMENTO = "data_nasc";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_TELEFONE = "telefone";

    }

    public static abstract class VeiculoRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "veiculo";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ANO = "ano";
        public static final String COLUMN_NAME_MARCA = "marca";
        public static final String COLUMN_NAME_PLACA = "placa";
        public static final String COLUMN_NAME_PASSAGEIROS = "passageiros";
        public static final String COLUMN_NAME_ID_MOTORISTA = "id_motorista";

    }

    public static abstract class ChamadaRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "chamada";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_VALUE = "valor";
        public static final String COLUMN_NAME_LOCAL_ORIGEM = "origem";
        public static final String COLUMN_NAME_DATE_ORIGEM = "origem_data";
        public static final String COLUMN_NAME_LOCAL_DESTINO = "destino";
        public static final String COLUMN_NAME_DATE_DESTINO = "dest_data";
        //public static final String COLUMN_NAME_LOCAL_ORIGEM_LATITUDE = "origem_latitude";
        //public static final String COLUMN_NAME_LOCAL_ORIGEM_LONGITUDE = "origem_dlongitude";
        //public static final String COLUMN_NAME_LOCAL_DESTINO_LATITUDE = "dest_latitude";
        //public static final String COLUMN_NAME_LOCAL_DESTINO_LONGITUDE = "dest_longitude";
    }

    public static abstract class AtendenteRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "atendente";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "nome";
        public static final String COLUMN_NAME_SALARIO = "salario";
        public static final String COLUMN_NAME_CPF = "cpf";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_TELEFONE = "telefone";

    }

    public static abstract class UsuarioRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "nome";
        public static final String COLUMN_NAME_CPF = "cpf";
        public static final String COLUMN_NAME_TELEFONE = "telefone";
        public static final String COLUMN_NAME_RUA = "rua";
        public static final String COLUMN_NAME_CIDADE = "cidade";
        public static final String COLUMN_NAME_ESTADO = "estado";
        //public static final String COLUMN_NAME_LATITUDE = "latitude";
        //public static final String COLUMN_NAME_LONGITUDE = "longitude";

    }

    public static abstract class SolicitaRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "solicita_taxi";
        public static final String COLUMN_NAME_ID_USUARIO = "id_usuario";
        public static final String COLUMN_NAME_ID_ATENDENTE = "id_atendente";
    }

    public static abstract class RegistraRegisterEntry implements BaseColumns {

        public static final String TABLE_NAME = "registra_chamada";
        public static final String COLUMN_NAME_ID_MOTORISTA = "id_motorista";
        public static final String COLUMN_NAME_ID_ATENDENTE = "id_atendente";
        public static final String COLUMN_NAME_ID_CHAMADA = "id_chamada";
    }



}
