package project.uno.data_structures;

import java.util.Random;

public class MyStackArray<T> implements MyStackInterface<T>{

    int size;
    private Object[] array;
    int top;

    public MyStackArray(int size){
        this.top = -1;
        this.size = size;
        this.array = new Object[size];
    }

    @Override
    public void push(T value) {
        if(isFull()) {
	        throw new MyException("\nERRO: pilha cheia [valor="+value+"]!!!\n" );
	    }

	    top = top + 1;
	    array[top] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        T retorno;
		
	    if(isEmpty()) {
	        throw new MyException( "\nERRO: pilha vazia!!!\n" );
	    }

        retorno = (T) array[top];
        top = top - 1;

	    return retorno;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T search(int index){
        if(index < 0 || index > top){
            throw new MyException("ERRO: indice inválido");
        }
        return (T) array[top-index];
    }


    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        T retorno;
		
	    if(isEmpty()) {
	        throw new MyException( "\nERRO: pilha vazia!!!\n" );
	    }
	    
	    retorno = (T) array[top];

	    return retorno;
    }

    @SuppressWarnings("unchecked")
    public void shuffle(){
        Random rand = new Random();
        for(int i=size-1;i>0;i--){
            int j = rand.nextInt(i+1);
            T temp = (T) array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    @Override
    public boolean isEmpty() {
        if(top == -1){
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if(top == size-1){
            return true;
        }
        return false;
    }

    @Override
    public void show() {
        for(int i=0; i <= top; i++) {
	        System.out.println("posicao " + i + " = " + array[i] + "\n");
	    }
    }

    @Override
    public int size() {
        return this.size;
    }
    
}
