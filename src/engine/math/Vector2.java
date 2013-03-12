package engine.math;

import javax.vecmath.Tuple2d;
import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;

/**
 * A 2D Vector implementation
 * 
 * Currently the same as Vector2f in javax.vecmath
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:30:59
 *
 */
public class Vector2 extends Vector2f{

	public Vector2() {
		super();
	}

	public Vector2(float arg0, float arg1) {
		super(arg0, arg1);
	}

	public Vector2(float[] arg0) {
		super(arg0);
	}

	public Vector2(Tuple2d arg0) {
		super(arg0);
	}

	public Vector2(Tuple2f arg0) {
		super(arg0);
	}

	public Vector2(Vector2d arg0) {
		super(arg0);
	}

	public Vector2(Vector2f arg0) {
		super(arg0);
	}

}
