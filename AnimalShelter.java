import java.util.LinkedList;

public class AnimalShelter {


    static abstract class Animal {
        private String name;
        private int arrivalTime;

        public Animal(String name, int arrivalTime) {
            this.name = name;
            this.arrivalTime = arrivalTime;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public String getName() {
            return name;
        }
    }


    static class Dog extends Animal {
        public Dog(String name, int arrivalTime) {
            super(name, arrivalTime);
        }
    }

    // Cat subclass
    static class Cat extends Animal {
        public Cat(String name, int arrivalTime) {
            super(name, arrivalTime);
        }
    }

    // AnimalShelter class
    private LinkedList<Dog> dogs;
    private LinkedList<Cat> cats;
    private int order;

    public AnimalShelter() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
        order = 0;
    }

    // Add a dog or cat
    public void enqueue(Animal animal) {
        if (animal instanceof Dog) {
            dogs.add(new Dog(animal.getName(), order++));
        } else if (animal instanceof Cat) {
            cats.add(new Cat(animal.getName(), order++));
        }
    }

    // Dequeue any animal (the oldest animal; dog or cat)
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) return null;
        if (dogs.isEmpty()) return dequeueCat();
        if (cats.isEmpty()) return dequeueDog();

        Dog oldestDog = dogs.peek();
        Cat oldestCat = cats.peek();
        if (oldestDog.getArrivalTime() < oldestCat.getArrivalTime()) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    // Dequeue the oldest dog
    public Dog dequeueDog() {
        return dogs.isEmpty() ? null : dogs.poll();
    }

    // Dequeue the oldest cat
    public Cat dequeueCat() {
        return cats.isEmpty() ? null : cats.poll();
    }


    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        shelter.enqueue(new Dog("Buddy", 0));
        shelter.enqueue(new Cat("Squeak", 0));
        shelter.enqueue(new Dog("Steve", 0));
        shelter.enqueue(new Cat("FluffyPants", 0));

        System.out.println("Dequeue Any: " + shelter.dequeueAny().getName());  // Expected: Buddy
        System.out.println("Dequeue Dog: " + shelter.dequeueDog().getName());  // Expected: Rex
        System.out.println("Dequeue Cat: " + shelter.dequeueCat().getName());  // Expected: Whiskers
    }
}
