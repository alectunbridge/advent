class Worker {
    char node = Character.MIN_VALUE;
    int secondsRemaning = 0;


    char doWork() {
        char completed = Character.MIN_VALUE;
        if(node != Character.MIN_VALUE){
            secondsRemaning--;
            if(secondsRemaning == 0){
                completed = node;
                node = Character.MIN_VALUE;
            }
        }
        return completed;
    }

    boolean assignNode(char node) {
        if(Character.MIN_VALUE == this.node){
            this.node = node;
            this.secondsRemaning = node - 'A' + 61;
            return true;
        }
        return false;
    }

    boolean isBusy() {
        return Character.MIN_VALUE != node;
    }
}
