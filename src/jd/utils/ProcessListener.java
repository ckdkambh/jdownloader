//    jDownloader - Downloadmanager
//    Copyright (C) 2008  JD-Team jdownloader@freenet.de
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jd.utils;

public class ProcessListener {
    /**
     * 
     * @param exec
     *            Source Object
     * @param latest
     *            Die zuletzte gelesene zeile. \b chars werden als new line char
     *            angesehen
     * @param sb
     *            Der complette BUffer (exec.getInputStringBuffer()|
     *            exec.getErrorStringBuffer())
     */
    public boolean handle_onProcess = true;
    public boolean handle_onBufferChanged = true;
    public int interruptafter = -1;

    public void onProcess(Executer exec, String latestLine, DynByteBuffer sb) {
    };

    public void onBufferChanged(Executer exec, DynByteBuffer sb, DynByteBuffer origin) {
    };

}
