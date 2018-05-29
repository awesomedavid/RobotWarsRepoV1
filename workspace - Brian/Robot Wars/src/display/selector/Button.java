package display.selector;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.InputListener;

public class Button<T> extends InteractiveUIElement{
	private T returnOBJ;
	float borderSize = 1;
	
	public Button(float x, float y, float w, float h, T returnOBJ) {
		super(x, y, w, h);
		this.returnOBJ = returnOBJ;
		
		this.addColor(0, new Color(64, 64, 64));
		this.addColor(1, new Color(32, 32, 32));
		this.addColor(2, new Color(200, 200, 200));
		
		this.addText(0, "Button");
	}
	
	public T getReturnOBJ() {
		return this.returnOBJ;
	}
	
	public void setReturnOBJ(T returnOBJ) {
		this.returnOBJ = returnOBJ;
	}
	
	public boolean isPressed() {
		return isMouseOver() && isMouseButtonDown(0);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if(isPressed()) {
			g.setColor(this.getColor(1).darker(.5f));
			g.fillRect(x, y, w, h);
			g.setColor(this.getColor(0).darker(.5f));
			g.fillRect(x + borderSize, y + borderSize, w - borderSize * 2, h - borderSize * 2);
			g.setColor(this.getColor(2).darker(.5f));
			g.drawString(this.getText(0), x + w / 2 - g.getFont().getWidth(this.getText(0))/2, y + h / 2-g.getFont().getHeight(this.getText(0))/2);
		}
		else if(this.isMouseOver()) {
			g.setColor(this.getColor(1).brighter(.5f));
			g.fillRect(x, y, w, h);
			g.setColor(this.getColor(0).brighter(.5f));
			g.fillRect(x + borderSize, y + borderSize, w - borderSize * 2, h - borderSize * 2);
			g.setColor(this.getColor(2).brighter(.5f));
			g.drawString(this.getText(0), x + w / 2 - g.getFont().getWidth(this.getText(0))/2, y + h / 2-g.getFont().getHeight(this.getText(0))/2);
		}
		else {
			g.setColor(this.getColor(1));
			g.fillRect(x, y, w, h);
			g.setColor(this.getColor(0));
			g.fillRect(x + borderSize, y + borderSize, w - borderSize * 2, h - borderSize * 2);
			g.setColor(this.getColor(2));
			g.drawString(this.getText(0), x + w / 2 - g.getFont().getWidth(this.getText(0))/2, y + h / 2-g.getFont().getHeight(this.getText(0))/2);
		}
	}
}
