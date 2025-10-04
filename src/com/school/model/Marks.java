package com.school.model;

public class Marks {
    private int physics;
    private int chemistry;
    private int maths;

    public Marks(int physics, int chemistry, int maths) {
        this.physics = physics;
        this.chemistry = chemistry;
        this.maths = maths;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }
}
