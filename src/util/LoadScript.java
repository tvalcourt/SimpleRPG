package util;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

/**
 * Handles loading scripts through LuaJava and executing them
 * Credit to Gustavo Henrique Soares de Oliveira Lyrio of the Catholic University of Rio de Janeiro for this class
 */
public class LoadScript {
    LuaState luaState;
    /**
     * Constructor
     * @param fileName File name with Lua script.
     */
    public LoadScript(final String fileName) {
        this.luaState = LuaStateFactory.newLuaState();
        this.luaState.openLibs();
        this.luaState.LdoFile(fileName);
    }
    /**
     * Ends the use of Lua environment.
     */
    public void closeScript() {
        this.luaState.close();
    }

    /**
     * Returns the current LuaState object
     * @return This instance of the LuaState object
     */
    public LuaState getLuaState(){
        return this.luaState;
    }

    /**
     * Call a Lua function inside the Lua script to insert
     * data into a Java object passed as parameter
     * @param functionName Name of Lua function.
     * @param obj A Java object.
     */
    public void runScriptFunction(String functionName, Object obj, int args, int returnArgs) {
        this.luaState.getGlobal(functionName);
        this.luaState.pushJavaObject(obj);
        this.luaState.call(args, returnArgs);
        this.luaState.pop(args);
    }

    /**
     * Call a Lua function inside the Lua script to insert
     * data into a Java object passed as parameter
     * @param functionName Name of Lua function.
     * @param obj_1 A Java object.
     * @param obj_2 A Java object.
     */
    void runScriptFunction(String functionName, Object obj_1, Object obj_2) {
        this.luaState.getGlobal(functionName);
        this.luaState.pushJavaObject(obj_1);
        this.luaState.pushJavaObject(obj_2);
        this.luaState.call(2, 0);
    }
}