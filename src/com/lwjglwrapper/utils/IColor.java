
package com.lwjglwrapper.utils;

import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.utils.math.MathUtils;
import java.awt.Color;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

public class IColor implements Paint{

    public static final IColor TRANSPARENT = new IColor(0, 0, 0, 0);
    
    //Colors taken from javafx.scene.paint.Color class
    public static final IColor ALICEBLUE = new IColor(0.9411765f, 0.972549f, 1.0f);

    public static final IColor ANTIQUEWHITE = new IColor(0.98039216f, 0.92156863f, 0.84313726f);

    public static final IColor AQUA = new IColor(0.0f, 1.0f, 1.0f);

    public static final IColor AQUAMARINE = new IColor(0.49803922f, 1.0f, 0.83137256f);

    public static final IColor AZURE = new IColor(0.9411765f, 1.0f, 1.0f);

    public static final IColor BEIGE = new IColor(0.9607843f, 0.9607843f, 0.8627451f);

    public static final IColor BISQUE = new IColor(1.0f, 0.89411765f, 0.76862746f);

    public static final IColor BLACK = new IColor(0.0f, 0.0f, 0.0f);

    public static final IColor BLANCHEDALMOND = new IColor(1.0f, 0.92156863f, 0.8039216f);

    public static final IColor BLUE = new IColor(0.0f, 0.0f, 1.0f);

    public static final IColor BLUEVIOLET = new IColor(0.5411765f, 0.16862746f, 0.8862745f);

    public static final IColor BROWN = new IColor(0.64705884f, 0.16470589f, 0.16470589f);

    public static final IColor BURLYWOOD = new IColor(0.87058824f, 0.72156864f, 0.5294118f);

    public static final IColor CADETBLUE = new IColor(0.37254903f, 0.61960787f, 0.627451f);

    public static final IColor CHARTREUSE = new IColor(0.49803922f, 1.0f, 0.0f);

    public static final IColor CHOCOLATE = new IColor(0.8235294f, 0.4117647f, 0.11764706f);

    public static final IColor CORAL = new IColor(1.0f, 0.49803922f, 0.3137255f);

    public static final IColor CORNFLOWERBLUE = new IColor(0.39215687f, 0.58431375f, 0.92941177f);

    public static final IColor CORNSILK = new IColor(1.0f, 0.972549f, 0.8627451f);

    public static final IColor CRIMSON = new IColor(0.8627451f, 0.078431375f, 0.23529412f);

    public static final IColor CYAN = new IColor(0.0f, 1.0f, 1.0f);

    public static final IColor DARKBLUE = new IColor(0.0f, 0.0f, 0.54509807f);

    public static final IColor DARKCYAN = new IColor(0.0f, 0.54509807f, 0.54509807f);

    public static final IColor DARKGOLDENROD = new IColor(0.72156864f, 0.5254902f, 0.043137256f);

    public static final IColor DARKGRAY = new IColor(0.6627451f, 0.6627451f, 0.6627451f);

    public static final IColor DARKGREEN = new IColor(0.0f, 0.39215687f, 0.0f);

    public static final IColor DARKGREY  = DARKGRAY;

    public static final IColor DARKKHAKI = new IColor(0.7411765f, 0.7176471f, 0.41960785f);

    public static final IColor DARKMAGENTA = new IColor(0.54509807f, 0.0f, 0.54509807f);

    public static final IColor DARKOLIVEGREEN = new IColor(0.33333334f, 0.41960785f, 0.18431373f);

    public static final IColor DARKORANGE = new IColor(1.0f, 0.54901963f, 0.0f);

    public static final IColor DARKORCHID = new IColor(0.6f, 0.19607843f, 0.8f);

    public static final IColor DARKRED = new IColor(0.54509807f, 0.0f, 0.0f);

    public static final IColor DARKSALMON = new IColor(0.9137255f, 0.5882353f, 0.47843137f);

    public static final IColor DARKSEAGREEN = new IColor(0.56078434f, 0.7372549f, 0.56078434f);

    public static final IColor DARKSLATEBLUE = new IColor(0.28235295f, 0.23921569f, 0.54509807f);

    public static final IColor DARKSLATEGRAY = new IColor(0.18431373f, 0.30980393f, 0.30980393f);

    public static final IColor DARKSLATEGREY = DARKSLATEGRAY;

    public static final IColor DARKTURQUOISE = new IColor(0.0f, 0.80784315f, 0.81960785f);

    public static final IColor DARKVIOLET = new IColor(0.5803922f, 0.0f, 0.827451f);

    public static final IColor DEEPPINK = new IColor(1.0f, 0.078431375f, 0.5764706f);

    public static final IColor DEEPSKYBLUE = new IColor(0.0f, 0.7490196f, 1.0f);

    public static final IColor DIMGRAY = new IColor(0.4117647f, 0.4117647f, 0.4117647f);

    public static final IColor DIMGREY = DIMGRAY;

    public static final IColor DODGERBLUE = new IColor(0.11764706f, 0.5647059f, 1.0f);

    public static final IColor FIREBRICK = new IColor(0.69803923f, 0.13333334f, 0.13333334f);

    public static final IColor FLORALWHITE = new IColor(1.0f, 0.98039216f, 0.9411765f);

    public static final IColor FORESTGREEN = new IColor(0.13333334f, 0.54509807f, 0.13333334f);

    public static final IColor FUCHSIA = new IColor(1.0f, 0.0f, 1.0f);

    public static final IColor GAINSBORO = new IColor(0.8627451f, 0.8627451f, 0.8627451f);

    public static final IColor GHOSTWHITE = new IColor(0.972549f, 0.972549f, 1.0f);

    public static final IColor GOLD = new IColor(1.0f, 0.84313726f, 0.0f);

    public static final IColor GOLDENROD = new IColor(0.85490197f, 0.64705884f, 0.1254902f);

    public static final IColor GRAY = new IColor(0.5019608f, 0.5019608f, 0.5019608f);

    public static final IColor GREEN = new IColor(0.0f, 0.5019608f, 0.0f);

    public static final IColor GREENYELLOW = new IColor(0.6784314f, 1.0f, 0.18431373f);

    public static final IColor GREY = GRAY;

    public static final IColor HONEYDEW = new IColor(0.9411765f, 1.0f, 0.9411765f);

    public static final IColor HOTPINK = new IColor(1.0f, 0.4117647f, 0.7058824f);

    public static final IColor INDIANRED = new IColor(0.8039216f, 0.36078432f, 0.36078432f);

    public static final IColor INDIGO = new IColor(0.29411766f, 0.0f, 0.50980395f);

    public static final IColor IVORY = new IColor(1.0f, 1.0f, 0.9411765f);

    public static final IColor KHAKI = new IColor(0.9411765f, 0.9019608f, 0.54901963f);

    public static final IColor LAVENDER = new IColor(0.9019608f, 0.9019608f, 0.98039216f);

    public static final IColor LAVENDERBLUSH = new IColor(1.0f, 0.9411765f, 0.9607843f);

    public static final IColor LAWNGREEN = new IColor(0.4862745f, 0.9882353f, 0.0f);

    public static final IColor LEMONCHIFFON = new IColor(1.0f, 0.98039216f, 0.8039216f);

    public static final IColor LIGHTBLUE = new IColor(0.6784314f, 0.84705883f, 0.9019608f);

    public static final IColor LIGHTCORAL = new IColor(0.9411765f, 0.5019608f, 0.5019608f);

    public static final IColor LIGHTCYAN = new IColor(0.8784314f, 1.0f, 1.0f);

    public static final IColor LIGHTGOLDENRODYELLOW = new IColor(0.98039216f, 0.98039216f, 0.8235294f);

    public static final IColor LIGHTGRAY = new IColor(0.827451f, 0.827451f, 0.827451f);

    public static final IColor LIGHTGREEN = new IColor(0.5647059f, 0.93333334f, 0.5647059f);

    public static final IColor LIGHTGREY = LIGHTGRAY;

    public static final IColor LIGHTPINK = new IColor(1.0f, 0.7137255f, 0.75686276f);

    public static final IColor LIGHTSALMON = new IColor(1.0f, 0.627451f, 0.47843137f);

    public static final IColor LIGHTSEAGREEN = new IColor(0.1254902f, 0.69803923f, 0.6666667f);

    public static final IColor LIGHTSKYBLUE = new IColor(0.5294118f, 0.80784315f, 0.98039216f);

    public static final IColor LIGHTSLATEGRAY = new IColor(0.46666667f, 0.53333336f, 0.6f);

    public static final IColor LIGHTSLATEGREY       = LIGHTSLATEGRAY;

    public static final IColor LIGHTSTEELBLUE = new IColor(0.6901961f, 0.76862746f, 0.87058824f);

    public static final IColor LIGHTYELLOW = new IColor(1.0f, 1.0f, 0.8784314f);

    public static final IColor LIME = new IColor(0.0f, 1.0f, 0.0f);

    public static final IColor LIMEGREEN = new IColor(0.19607843f, 0.8039216f, 0.19607843f);

    public static final IColor LINEN = new IColor(0.98039216f, 0.9411765f, 0.9019608f);

    public static final IColor MAGENTA = new IColor(1.0f, 0.0f, 1.0f);

    public static final IColor MAROON = new IColor(0.5019608f, 0.0f, 0.0f);

    public static final IColor MEDIUMAQUAMARINE = new IColor(0.4f, 0.8039216f, 0.6666667f);

    public static final IColor MEDIUMBLUE = new IColor(0.0f, 0.0f, 0.8039216f);

    public static final IColor MEDIUMORCHID = new IColor(0.7294118f, 0.33333334f, 0.827451f);

    public static final IColor MEDIUMPURPLE = new IColor(0.5764706f, 0.4392157f, 0.85882354f);

    public static final IColor MEDIUMSEAGREEN = new IColor(0.23529412f, 0.7019608f, 0.44313726f);

    public static final IColor MEDIUMSLATEBLUE = new IColor(0.48235294f, 0.40784314f, 0.93333334f);

    public static final IColor MEDIUMSPRINGGREEN = new IColor(0.0f, 0.98039216f, 0.6039216f);

    public static final IColor MEDIUMTURQUOISE = new IColor(0.28235295f, 0.81960785f, 0.8f);

    public static final IColor MEDIUMVIOLETRED = new IColor(0.78039217f, 0.08235294f, 0.52156866f);

    public static final IColor MIDNIGHTBLUE = new IColor(0.09803922f, 0.09803922f, 0.4392157f);

    public static final IColor MINTCREAM = new IColor(0.9607843f, 1.0f, 0.98039216f);

    public static final IColor MISTYROSE = new IColor(1.0f, 0.89411765f, 0.88235295f);

    public static final IColor MOCCASIN = new IColor(1.0f, 0.89411765f, 0.70980394f);

    public static final IColor NAVAJOWHITE = new IColor(1.0f, 0.87058824f, 0.6784314f);

    public static final IColor NAVY = new IColor(0.0f, 0.0f, 0.5019608f);

    public static final IColor OLDLACE = new IColor(0.99215686f, 0.9607843f, 0.9019608f);

    public static final IColor OLIVE = new IColor(0.5019608f, 0.5019608f, 0.0f);

    public static final IColor OLIVEDRAB = new IColor(0.41960785f, 0.5568628f, 0.13725491f);

    public static final IColor ORANGE = new IColor(1.0f, 0.64705884f, 0.0f);

    public static final IColor ORANGERED = new IColor(1.0f, 0.27058825f, 0.0f);

    public static final IColor ORCHID = new IColor(0.85490197f, 0.4392157f, 0.8392157f);

    public static final IColor PALEGOLDENROD = new IColor(0.93333334f, 0.9098039f, 0.6666667f);

    public static final IColor PALEGREEN = new IColor(0.59607846f, 0.9843137f, 0.59607846f);

    public static final IColor PALETURQUOISE = new IColor(0.6862745f, 0.93333334f, 0.93333334f);

    public static final IColor PALEVIOLETRED = new IColor(0.85882354f, 0.4392157f, 0.5764706f);

    public static final IColor PAPAYAWHIP = new IColor(1.0f, 0.9372549f, 0.8352941f);

    public static final IColor PEACHPUFF = new IColor(1.0f, 0.85490197f, 0.7254902f);

    public static final IColor PERU = new IColor(0.8039216f, 0.52156866f, 0.24705882f);

    public static final IColor PINK = new IColor(1.0f, 0.7529412f, 0.79607844f);

    public static final IColor PLUM = new IColor(0.8666667f, 0.627451f, 0.8666667f);

    public static final IColor POWDERBLUE = new IColor(0.6901961f, 0.8784314f, 0.9019608f);

    public static final IColor PURPLE = new IColor(0.5019608f, 0.0f, 0.5019608f);

    public static final IColor RED = new IColor(1.0f, 0.0f, 0.0f);

    public static final IColor ROSYBROWN = new IColor(0.7372549f, 0.56078434f, 0.56078434f);

    public static final IColor ROYALBLUE = new IColor(0.25490198f, 0.4117647f, 0.88235295f);

    public static final IColor SADDLEBROWN = new IColor(0.54509807f, 0.27058825f, 0.07450981f);

    public static final IColor SALMON = new IColor(0.98039216f, 0.5019608f, 0.44705883f);

    public static final IColor SANDYBROWN = new IColor(0.95686275f, 0.6431373f, 0.3764706f);

    public static final IColor SEAGREEN = new IColor(0.18039216f, 0.54509807f, 0.34117648f);

    public static final IColor SEASHELL = new IColor(1.0f, 0.9607843f, 0.93333334f);

    public static final IColor SIENNA = new IColor(0.627451f, 0.32156864f, 0.1764706f);

    public static final IColor SILVER = new IColor(0.7529412f, 0.7529412f, 0.7529412f);

    public static final IColor SKYBLUE = new IColor(0.5294118f, 0.80784315f, 0.92156863f);

    public static final IColor SLATEBLUE = new IColor(0.41568628f, 0.3529412f, 0.8039216f);

    public static final IColor SLATEGRAY = new IColor(0.4392157f, 0.5019608f, 0.5647059f);

    public static final IColor SLATEGREY            = SLATEGRAY;

    public static final IColor SNOW = new IColor(1.0f, 0.98039216f, 0.98039216f);

    public static final IColor SPRINGGREEN = new IColor(0.0f, 1.0f, 0.49803922f);

    public static final IColor STEELBLUE = new IColor(0.27450982f, 0.50980395f, 0.7058824f);

    public static final IColor TAN = new IColor(0.8235294f, 0.7058824f, 0.54901963f);

    public static final IColor TEAL = new IColor(0.0f, 0.5019608f, 0.5019608f);

    public static final IColor THISTLE = new IColor(0.84705883f, 0.7490196f, 0.84705883f);

    public static final IColor TOMATO = new IColor(1.0f, 0.3882353f, 0.2784314f);

    public static final IColor TURQUOISE = new IColor(0.2509804f, 0.8784314f, 0.8156863f);

    public static final IColor VIOLET = new IColor(0.93333334f, 0.50980395f, 0.93333334f);

    public static final IColor WHEAT = new IColor(0.9607843f, 0.87058824f, 0.7019608f);

    public static final IColor WHITE = new IColor(1.0f, 1.0f, 1.0f);

    public static final IColor WHITESMOKE = new IColor(0.9607843f, 0.9607843f, 0.9607843f);
    
    public static final IColor YELLOW = new IColor(1.0f, 1.0f, 0.0f);
    
    public static final IColor YELLOWGREEN = new IColor(0.6039216f, 0.8039216f, 0.19607843f);
    
    public final float r, g, b, a;

    public IColor(float r, float g, float b, float a) {
        r = MathUtils.clamp(0f, r, 1f);
        g = MathUtils.clamp(0f, g, 1f);
        b = MathUtils.clamp(0f, b, 1f);
        a = MathUtils.clamp(0f, a, 1f);
        
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public IColor(float r, float g, float b) {
        this(r, g, b, 1);
    }
    
    public IColor(float gray, float a) {
        this(gray, gray, gray, a);
    }
    
    public IColor(float gray) {
        this(gray, 1);
    }

    public IColor(IColor color) {
        this(color.r, color.g, color.b, color.a);
    }
    
    public NVGColor toNanoVGColor() {
        return NanoVG.nvgRGBAf(r, g, b, a, NVGColor.create());
    }
    
    public Color toAWTColor() {
        return new Color(r, g, b, a);
    }
    
    public Vector3f to3DVector() {
        return new Vector3f(r, g, b);
    }
    
    public Vector4f to4DVector() {
        return new Vector4f(r, g, b, a);
    }
    
    public static IColor hsv(float h, float s, float v) {
        return awt(Color.getHSBColor(h, s, v));
    }
    
    public static IColor array(float[] comp) {
        switch (comp.length) {
            case 0: return null;
            case 1: return new IColor(comp[0]);
            case 2: return new IColor(comp[0], comp[1]);
            case 3: return new IColor(comp[0], comp[1], comp[2]);
            default:return new IColor(comp[0], comp[1], comp[2], comp[3]);
        }
        
    }
    
    public static IColor awt(Color color) {
        return IColor.array(color.getComponents(new float[4]));
    }
    
    public static IColor nanovg(NVGColor nvgColor) {
        return new IColor(nvgColor.r(), nvgColor.g(), nvgColor.b(), nvgColor.a());
    }
    
    public static IColor vec3(Vector3f vec3) {
        return new IColor(vec3.x, vec3.y, vec3.z);
    }
    
    public static IColor vec4(Vector4f vec4) {
        return new IColor(vec4.x, vec4.y, vec4.z, vec4.w);
    }

    @Override
    public void fill(long nvgID) {
        NanoVG.nvgFillColor(nvgID, toNanoVGColor());
        NanoVG.nvgFill(nvgID);
    }

    @Override
    public void stroke(long nvgID) {
        NanoVG.nvgStrokeColor(nvgID, toNanoVGColor());
        NanoVG.nvgStroke(nvgID);
    }

    @Override
    public void text(long nvgID) {
        NanoVG.nvgFillColor(nvgID, toNanoVGColor());
    }
    
    public float[] hsv() {
        Color awtColor = toAWTColor();
        return Color.RGBtoHSB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), new float[3]);
    }

    public IColor alpha(float a) {
        return new IColor(r, g, b, a);
    }

    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ", " + a + ")";
    }
    
    public IColor brighter() {
        return awt(toAWTColor().brighter());
    }
    
    public IColor darker() {
        return awt(toAWTColor().darker());
    }
    
    public IColor scale(float factor) {
        return new IColor(r * factor, g * factor, b * factor, a);
    }

    public IColor tint(float tint) {
        return new IColor(r + tint, g + tint, b + tint, a);
    }
    
}
