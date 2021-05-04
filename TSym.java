import java.util.*;

/**
 * The TSym class defines a symbol-table entry.
 * Each TSym contains a type (a Type).
 */
public class TSym {
    private Type type;
    private boolean global;

    private int offset;

    public TSym(Type type) {
        this.type = type;
        this.offset = 0;
        this.global = false;
    }

    public Type getType() {
        return type;
    }

    public void setIsGlobal(boolean global) {
        this.global = global;  
    }

    public boolean isGlobal() {
        return global;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String toString() {
        return type.toString();
    }
}

/**
 * The FnSym class is a subclass of the TSym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnSym extends TSym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    private int sizeLocals;
    private int sizeParams;

    public FnSym(Type type, int numparams) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }

    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public void setSizeLocals(int size) {
        this.sizeLocals = size;
    }

    public void setSizeParams(int size) {
        this.sizeParams = size;
    }

    public int getSizeLocals() {
        return this.sizeLocals;
    }

    public int getSizeParams() {
        return this.sizeParams;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/**
 * The StructSym class is a subclass of the TSym class just for variables
 * declared to be a struct type.
 * Each StructSym contains a symbol table to hold information about its
 * fields.
 */
class StructSym extends TSym {
    // new fields
    private IdNode structType;  // name of the struct type
    

    public StructSym(IdNode id) {
        super(new StructType(id));
        structType = id;
    }

    public IdNode getStructType() {
        return structType;
    }
}

/**
 * The StructDefSym class is a subclass of the TSym class just for the
 * definition of a struct type.
 * Each StructDefSym contains a symbol table to hold information about its
 * fields.
 */
class StructDefSym extends TSym {
    // new fields
    private SymTable symTab;
    private int size;

    public StructDefSym(SymTable table) {
        super(new StructDefType());
        symTab = table;
    }

    public SymTable getSymTable() {
        return symTab;
    }
    public void setSize(int newSize) {
        size = newSize;
    }
    public int getSize() {
        return size;
    }
}
