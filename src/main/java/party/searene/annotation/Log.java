package party.searene.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by searene on 11/28/16.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Log {
}
