/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco;

import java.util.Random;

/**
 *
 * @author Welcome
 */
public enum Level {
    ONE(20, 3, 0, 0.5f, 2, 1, 5.5f, 6f, 150, 200),
    TWO(30, 4, 0, 0.6f, 2, 1, 3.5f, 3.75f, 150, 200),
    THREE(40, 4, 0, 0.65f, 2, 1, 2.75f, 3.25f, 150, 200),
    FOUR(30, 3, 0, 0.6f, 2, 2, 3.25f, 3.75f, 150, 200),
    FIVE(45, 3, 0, 0.6f, 2, 2, 2.75f, 3.25f, 150, 200),
    SIX(60, 4, 0, 0.65f, 1.5f, 2, 2.75f, 3.25f, 150, 200),
    SEVEN(45, 3, 0, 0.4f, 2, 4, 3.5f, 4f, 150, 200),
    EIGHT(60, 3, 0, 0.6f, 2, 4, 3.2f, 3.7f, 150, 200),
    NINE(75, 4, 0, 0.6f, 2, 4, 2.75f, 3.25f, 150, 200),
    TEN(90, 4, 0, 0.65f, 2, 4, 2.75f, 3.25f, 150, 200);
    
    private static final Random random = new Random();

    private Level(int garbageRequirements, int garbageLimit, float deathHP, float spawnRate,
            float maxNoSpawnTime, int types, float maxAgeRangeFrom, float maxAgeRangeTo,
            float maxRadiusFrom, float maxRadiusTo) {
        this.garbageRequirements = garbageRequirements;
        this.garbageLimit = garbageLimit;
        this.deathHP = deathHP;
        this.spawnRate = spawnRate;
        this.maxNoSpawnTime = maxNoSpawnTime;
        this.types = types;
        this.maxAgeRangeFrom = maxAgeRangeFrom;
        this.maxAgeRangeTo = maxAgeRangeTo;
        this.maxRadiusFrom = maxRadiusFrom;
        this.maxRadiusTo = maxRadiusTo;
    }

    public int garbageRequirements, types, garbageLimit;
    public float deathHP, spawnRate, maxNoSpawnTime;
    public float maxAgeRangeFrom, maxAgeRangeTo;
    public float maxRadiusFrom, maxRadiusTo;
    
    public int generateType() {
        return random(1, types);
    }
    
    public float generateMaxAge() {
        return random(maxAgeRangeFrom, maxAgeRangeTo);
    }
    
    public float generateMaxRadius() {
        return random(maxRadiusFrom, maxRadiusTo);
    }
    
    private static int random(int from, int to) {
        return from + random.nextInt(to - from + 1);
    }
    
    private static float random(float from, float to) {
        return from + random.nextFloat() * (to - from);
    }
}
