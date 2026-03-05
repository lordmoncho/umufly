package es.um.atica;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
// import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaParameter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import jakarta.validation.Valid;

@AnalyzeClasses( packages = "es.um.atica.umufly" )
public class UmuFlyArquitecutraTests {

	@ArchTest
	static final ArchRule ninguna_interfaz_acaba_en_impl = classes().that().areInterfaces().should().haveSimpleNameNotEndingWith( "Impl" ).because( "Las interfaces no deben acabar en Impl" );

	@ArchTest
	static final ArchRule codigo_respeta_arquitectura_hexagonal = layeredArchitecture()// Define una estructura por capas
	.consideringAllDependencies() // Cojo todas las dependencias del proyecto
	.layer( "Domain" ).definedBy( "..domain.." ) // Defino la capa de dominio por todas las clases dentro de ..domain..
	.layer( "Application" ).definedBy( "..application.." ) // Defino la capa de aplicacion por todas las clases dentro de ..application..
	.layer( "Adapters" ).definedBy( "..adaptors.." )// Defino la capa de adaptadores por todas las clases dentro de ..adaptors..
	.whereLayer( "Domain" ).mayOnlyBeAccessedByLayers( "Application", "Adapters" ) // Indico que "imports" de la
	// capa de dominio pueden estar en Application y Adapters
	.whereLayer( "Application" ).mayOnlyBeAccessedByLayers( "Adapters" )// Indico que "imports" de la capa de
	// adaptadores pueden estar en Application (pero no en dominio)
	.whereLayer( "Adapters" ).mayNotBeAccessedByAnyLayer(); // Ninguna capa debe tener import de adapters

	private static final ArchCondition<JavaMethod> METODO_REST_VALIDA_PARAMETROS = new ArchCondition<>( "Rest debe tener @Valid o @Validated en parámetros @RequestBody" ) {

		@Override
		public void check( JavaMethod metodo, ConditionEvents events ) {
			boolean validaParametro = false;
			for ( JavaParameter parametro : metodo.getParameters() ) {
				// Compruebo si el metodo tiene RequestBody (JSON)
				if ( parametro.isAnnotatedWith( RequestBody.class ) ) {
					validaParametro = parametro.isAnnotatedWith( Valid.class ) || parametro.isAnnotatedWith( Validated.class ) || metodo.getOwner().isAnnotatedWith( Validated.class );
					if ( !validaParametro ) {
						String message = String.format( "El método %s tiene un @RequestBody sin validación", metodo.getFullName() );
						// Aniado un evento de violacion
						events.add( SimpleConditionEvent.violated( metodo, message ) );
					}
				}
			}
		}
	};

	@ArchTest
	static final ArchRule api_rest_debe_validar_datos_entrada = methods().that().areDeclaredInClassesThat().areAnnotatedWith( RestController.class ).and().arePublic().should( METODO_REST_VALIDA_PARAMETROS );

	private static final DescribedPredicate<JavaClass> IMPLEMENTA_ALGUNA_INTERFAZ = new DescribedPredicate<JavaClass>( "implementan alguna interfaz" ) {

		@Override
		public boolean test( JavaClass input ) {
			return !input.getInterfaces().isEmpty();
		}
	};

	@ArchTest
	static final ArchRule toda_implementacion_de_interfaz_acaba_imp = classes().that().areNotInterfaces().and().areNotEnums().and( IMPLEMENTA_ALGUNA_INTERFAZ ).should().haveSimpleNameEndingWith( "Impl" );

	@ArchTest
	static final ArchRule todo_dto_debe_terminar_dto = classes().that().resideInAPackage( "..dto" ).and().areNotEnums().and().areNotInterfaces().should().haveSimpleNameEndingWith( "DTO" ).because( "Los DTOs deben terminar en DTO" );

	@ArchTest
	static final ArchRule rest_controller_capa_adaptadores = classes().that().areAnnotatedWith( RestController.class ).should().resideInAPackage( "..adaptors.." );

	private static ArchCondition<JavaClass> tenerMaximoNMetodosPublicos( final int max ) {
		return new ArchCondition<>( "tener máximo " + max + " métodos públicos" ) {

			@Override
			public void check( JavaClass item, ConditionEvents events ) {
				int count = 0;
				for ( JavaMethod method : item.getMethods() ) {
					if ( method.getModifiers().contains( com.tngtech.archunit.core.domain.JavaModifier.PUBLIC ) ) {
						count++;
					}
				}

				if ( count > max ) {
					String message = String.format( "La clase %s tiene %d métodos públicos (máximo permitido: %d)", item.getName(), count, max );
					events.add( SimpleConditionEvent.violated( item, message ) );
				}
			}
		};
	}

	@ArchTest
	public static final ArchRule maximo_20_metodos_publicos = classes().that().haveSimpleNameNotEndingWith( "Entity" ).should( tenerMaximoNMetodosPublicos( 20 ) ).because( "Las clases no pueden tener mas de 20 metodos publicos" );

}
