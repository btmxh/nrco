/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.utils.math.MathUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Welcome
 */
public class ScoringHandler {

    private List<Integer> scores;

    public int fakeScore, score;
    public float fakeAccuracy, fakeHP, accuracy, hp;
    public int combo;
    public int maxCombo;
    public float fakeGarbages;
    
    public Map<Integer, Integer> scoreCount;

    public ScoringHandler() {
        scores = new ArrayList<>();
        scoreCount = new HashMap<>();

        accuracy = 100;
        hp = 100;
    }

    public void addScore(int score) {
        scores.add(score);
        updateHP(score);
        scoreCount.put(score, scoreCount.containsKey(score)? scoreCount.get(score)+1:1);
    }

    @Override
    public String toString() {
        return scores.toString();
    }

    public void calculateScores(float delta) {
        //Accuracy, Score, HP, Combo
        //points
        int nonComboScore = scores.stream().mapToInt((i) -> i).sum();
        combo = 0;
        for (int i = scores.size() - 1; i >= 0; i--) {
            if (scores.get(i) > 0) {
                combo++;
            } else {
                break;
            }
        }

        if (!scores.isEmpty()) {
            accuracy = (float) (scores.stream().mapToDouble((i) -> i).sum() / 3.0 / scores.size());
        } else {
            accuracy = 100;
        }

        hp -= delta * 5;
        hp = MathUtils.clamp(0f, hp, 100f);

        float currentCombo = 0;
        score = 0;
        for (int i = 0; i < scores.size(); i++) {
            int pt = scores.get(i);
            score += (1 + currentCombo / 50) * pt;
            if (scores.get(i) > 0) {
                currentCombo++;
            } else {
                currentCombo = 0;
            }
        }

        //Fake Points
        final float scoreStep = 10, accuStep = 1, hpStep = 1, garbageStep = 0.2f;

        if (nonComboScore > fakeScore) {
            fakeScore += scoreStep;
            if (fakeScore > score) {
                fakeScore = score;
            }
        } else {
            fakeScore -= scoreStep;
            if (fakeScore < score) {
                fakeScore = score;
            }
        }
        
        int garbages = garbages();
        if(garbages > fakeGarbages) {
            fakeGarbages += garbageStep;
            if(fakeGarbages > garbages) {
                fakeGarbages = garbages;
            }
        } else {
            fakeGarbages -= garbageStep;
            if(fakeGarbages < garbages) {
                fakeGarbages = garbages;
            }
        }

        if (hp > fakeHP) {
            fakeHP += hpStep;
            if (fakeHP > hp) {
                fakeHP = hp;
            }
        } else {
            fakeHP -= hpStep;
            if (fakeHP < hp) {
                fakeHP = hp;
            }
        }

        if (accuracy > fakeAccuracy) {
            fakeAccuracy += accuStep;
            if (fakeAccuracy > accuracy) {
                fakeAccuracy = accuracy;
            }
        } else {
            fakeAccuracy -= accuStep;
            if (fakeAccuracy < accuracy) {
                fakeAccuracy = accuracy;
            }
        }

        fakeAccuracy = round2(fakeAccuracy, 1);
        
        if(combo > maxCombo) {
            maxCombo = combo;
        }
    }

    private void updateHP(int score) {
        float deltaHP = 0;
        switch (score) {
            case 300:
                deltaHP = 10f;
                break;
            case 200:
                deltaHP = 8f;
                break;
            case 100:
                deltaHP = 6f;
                break;
            case 50:
                deltaHP = 2f;
                break;
            case 0:
                deltaHP = -10f;
                break;
        }
        hp += deltaHP;
    }

    public static float round2(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++) {
            pow *= 10;
        }
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }

    public int garbages() {
        return scores.size();
    }

    public boolean maxCombo() {
        return maxCombo == garbages();
    }

    public boolean perfect() {
        return scores.stream().allMatch(s -> s == 300);
    }

    public int stars() {
        if(perfect())   return 3;
        if(maxCombo())  return 2;
        else return 1;
    }

    public void end() {
        fakeAccuracy = accuracy;
        fakeGarbages = garbages();
        fakeHP = hp;
        fakeScore = score;
    }
}
