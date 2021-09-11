package pe.com.lima.verde.registrar.usuario.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_SEGURIDAD_USUARIOS", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO,
	        generator="native"
		  )
		  @GenericGenerator(
		          name = "native",
		          strategy = "native")
  @Column(name = "ID_USU")
  private Long id;

  @Column(name = "C_LOG_USR", nullable = true)
  private String username;

  @Column(name = "C_PASS_USR", nullable = true)
  private String password;

  @Column(name = "C_NOM_USR", nullable = true)
  private String nombres;

  @Column(name = "C_APE_USR", nullable = true)
  private String apellidos;

  @Column(name = "C_DOC", nullable = false)
  private String documento;
  
  @Column(name = "C_MAIL_USR", nullable = false)
  private String email;
  
  @Column(name = "N_CELULAR", nullable = true)
  private Long celular;
  
  @Column(name = "C_GEN_USR", nullable = true)
  private String genero;
}
