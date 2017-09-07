/*
 *  This file is part of the Haven & Hearth game client.
 *  Copyright (C) 2009 Fredrik Tolf <fredrik@dolda2000.com>, and
 *                     Björn Johannessen <johannessen.bjorn@gmail.com>
 *
 *  Redistribution and/or modification of this file is subject to the
 *  terms of the GNU Lesser General Public License, version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Other parts of this source tree adhere to other copying
 *  rights. Please see the file `COPYING' in the root directory of the
 *  source tree for details.
 *
 *  A copy the GNU Lesser General Public License is distributed along
 *  with the source tree of which this file is a part in the file
 *  `doc/LPGL-3'. If it is missing for any reason, please see the Free
 *  Software Foundation's website at <http://www.fsf.org/>, or write
 *  to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *  Boston, MA 02111-1307 USA
 */

package haven.render.gl;

import haven.render.*;
import javax.media.opengl.*;

public class GLRender implements Render {
    public final GLEnvironment env;
    private final BGL gl = new BufferBGL();
    private Applier state = null, init = null;

    GLRender(GLEnvironment env) {
	this.env = env;
    }

    public GLEnvironment env() {return(env);}

    private void apply(Pipe pipe) {
	if(init == null) {
	    init = (state = new Applier(env, pipe.copy())).clone();
	} else {
	    state.apply(gl, pipe);
	}
    }

    static boolean ephemeralp(Model m) {
	if((m.ind != null) && (m.ind.usage == DataBuffer.Usage.EPHEMERAL))
	    return(true);
	for(VertexArray.Buffer b : m.va.bufs) {
	    if(b.usage == DataBuffer.Usage.EPHEMERAL)
		return(true);
	}
	return(false);
    }

    public void draw(Pipe pipe, Model data) {
	apply(pipe);
	if(ephemeralp(data)) {
	    GLBuffer ind;
	    if(data.ind.usage == DataBuffer.Usage.EPHEMERAL) {
		ind = env.tempindex.get();
		
	    } else {
		ind = null;
	    }
	    GLBuffer vbuf = env.tempvertex.get();
	    
	} else {
	}
    }

    public void execute(GL2 gl) {
	synchronized(env.drawmon) {
	}
    }
}
