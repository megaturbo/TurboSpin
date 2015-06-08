
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.Arrays;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.main.jframe.jpanels.views.JPanelMain2D;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.RotationItem;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

public class TurboCanvas extends Canvas3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public TurboCanvas(GraphicsConfiguration arg0)
		{
		super(arg0);
		//parallepipedusorz init
		para = new Vertex3D[12];
		// colored vertices
		para[0] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.RED, 1);
		para[1] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.GREEN, 1);
		para[2] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLUE, 1);
		// black vertices
		para[3] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[4] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[5] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[6] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[7] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[8] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		// black vertices that may be in the axes
		para[9] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[10] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[11] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		for(int i = 0; i < para.length; i++)
			{
			paraBG.addChild(para[i]);
			}
		quaternionAxis = new Vertex3D(new Point3D(), new Point3D(), TurboColors.LIGHTGRAY, 2);
		addShape(quaternionAxis);

		// Universe
		universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		// Groups capabilities
		shapesBG.setCapability(BranchGroup.ALLOW_DETACH);
		trailBG.setCapability(BranchGroup.ALLOW_DETACH);
		mainTG.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		mainTG.setCapability(Group.ALLOW_CHILDREN_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		setBackgroundColor(TurboColors.WHITE);
		createAxisSystem();

		// Controls
		createMouseNavigation();

		// camera position at start
		Transform3D rotate = new Transform3D();
		rotate.lookAt(new Point3d(8, 8, 8), new Point3d(2, 2, 0), new Vector3d(-0.5, 5, 0));
		mainTG.setTransform(rotate);

		// add the branch to the universe
		mainTG.addChild(paraBG);
		mainBG.addChild(mainTG);

		// Faster rendering
		//				 mainBG.compile(); //this makes everything crash, for some reason
		paraBG.compile();

		universe.addBranchGraph(mainBG);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addParallelepiped(Vertex3D vertex)
		{
		Point3D A = vertex.getA();
		Point3D B = vertex.getB();
		// colored vertices
		para[0].set(new Vertex3D(A, new Point3D(B.getX(), A.getY(), A.getZ())));
		para[1].set(new Vertex3D(A, new Point3D(A.getX(), B.getY(), A.getZ())));
		para[2].set(new Vertex3D(A, new Point3D(A.getX(), A.getY(), B.getZ())));
		// black vertices
		para[3].set(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ())));
		para[4].set(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(B.getX(), A.getY(), B.getZ())));
		para[5].set(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(A.getX(), B.getY(), B.getZ())));
		para[6].set(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ())));
		para[7].set(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), A.getY(), B.getZ())));
		para[8].set(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(A.getX(), B.getY(), B.getZ())));
		// black vertices that may be in the axes
		para[9].set(new Vertex3D(new Point3D(A.getX(), B.getY(), B.getZ()), B));
		para[10].set(new Vertex3D(new Point3D(B.getX(), A.getY(), B.getZ()), B));
		para[11].set(new Vertex3D(new Point3D(B.getX(), B.getY(), A.getZ()), B));
		}

	public void addParallelepiped()
		{
		Shape3D shape = selectedShape;
		if (shape instanceof Point3D)
			{
			addParallelepiped(new Vertex3D(new Point3D(), (Point3D)shape));
			}
		else if (shape instanceof Vector3D)
			{
			addParallelepiped(new Vertex3D(new Vector3D(), (Vector3D)shape));
			}
		else if (shape instanceof Vertex3D)
			{
			addParallelepiped((Vertex3D)shape);
			}
		}

	public void setPanelMain2D(JPanelMain2D panel)
		{
		this.panel = panel;
		}

	public void createTrail()
		{
		removeTrail();
		if (selectedRotation != null && selectedShape != null)
			{
			if (selectedRotation instanceof Quaternion)
				{
				Quaternion q = (Quaternion)selectedRotation;
				Vector3D axis = QuaternionTools.getAxis(q);
				if (selectedShape instanceof Vector3D)
					{
					quaternionAxis.set(new Vertex3D(new Point3D(axis.multiply(-50.0)), new Point3D(axis.multiply(50.0))));
					createTrail((Quaternion)selectedRotation, (Vector3D)selectedShape, TurboColors.LIGHTGRAY);
					}
				else if (selectedShape instanceof Point3D)
					{
					quaternionAxis.set(new Vertex3D(new Point3D(axis.multiply(-50.0)), new Point3D(axis.multiply(50.0))));
					createTrail((Quaternion)selectedRotation, new Vector3D((Point3D)selectedShape), TurboColors.LIGHTGRAY);
					}
				else if (selectedShape instanceof Vertex3D)
					{
					quaternionAxis.set(new Vertex3D(new Point3D(axis.multiply(-50.0)), new Point3D(axis.multiply(50.0))));
					Point3D A = ((Vertex3D)selectedShape).getA();
					Point3D B = ((Vertex3D)selectedShape).getB();
					createTrail((Quaternion)selectedRotation, new Vector3D(A), TurboColors.LIGHTGRAY);
					createTrail((Quaternion)selectedRotation, new Vector3D(B), TurboColors.LIGHTGRAY);
					}
				}
			if (selectedRotation instanceof Matrix)
				{
				if (selectedShape instanceof Vector3D)
					{
					createTrail((Matrix)selectedRotation, (Vector3D)selectedShape);
					}
				else if (selectedShape instanceof Point3D)
					{
					createTrail((Matrix)selectedRotation, new Vector3D((Point3D)selectedShape));
					}
				else if (selectedShape instanceof Vertex3D)
					{
					createTrail((Matrix)selectedRotation, new Vector3D(((Vertex3D)selectedShape).getA()));
					createTrail((Matrix)selectedRotation, new Vector3D(((Vertex3D)selectedShape).getB()));
					}
				}
			}
		refresh();
		}

	public void addShape(Shape3D shape)
		{
		shapesBG.detach();
		shapesBG.addChild(shape);
		mainTG.addChild(shapesBG);
		refresh();
		}

	public void removeShape(Shape3D shape)
		{
		shapesBG.detach();
		shapesBG.removeChild(shape);
		mainTG.addChild(shapesBG);
		refresh();
		}

	/**
	 * Removes all vectors from the scene
	 */
	public void clear()
		{
		shapesBG.detach();
		shapesBG.removeAllChildren();
		mainTG.addChild(shapesBG);
		createAxisSystem();
		}

	/**
	 * redraws vectors, useful if they have been changed
	 */
	public void refresh()
		{
		addParallelepiped();
		repaint();
//		if(panel != null)
//			{
//				panel.repaint();
//				}
		}

	public void setSelected(Shape3D shape)
		{
		this.selectedShape = shape;
		createTrail();
		this.refresh();
		}

	public void setSelected(RotationItem rotationItem)
		{
		this.selectedRotation = rotationItem;
		createTrail();
		this.refresh();
		}

	public void unselectRotation()
		{
		this.selectedRotation = null;
		removeTrail();
		this.refresh();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void createTrail(Quaternion q, Vector3D v, Color3f color)
		{
		// LineStripArray
		double theta = QuaternionTools.getAngle(q);
		Vector3D axis = QuaternionTools.getAxis(q);
		Quaternion qslow = QuaternionTools.createRotationQuaternion(2.0 * Math.PI / 360.0, axis);
		theta *= 180.0 / Math.PI;
		Vector3D tmp = new Vector3D(v);

		LineStripArray lsa = new LineStripArray((int)theta + 2, GeometryArray.COORDINATES | GeometryArray.COLOR_3, new int[] { (int)theta + 2 });

		Point3d pointTmp = new Point3d(tmp.getA(), tmp.getB(), tmp.getC());
		Point3d[] pointsInit = new Point3d[(int)theta + 1];
		pointsInit[0] = new Point3d(pointTmp);

		for(int i = 0; i < (int)theta; i++)
			{
			tmp = QuaternionTools.rotation(tmp, qslow);
			pointTmp = new Point3d(tmp.getA(), tmp.getB(), tmp.getC());
			lsa.setCoordinate(i, pointTmp);
			pointsInit[i + 1] = new Point3d(pointTmp);
			}
		lsa.setCoordinate((int)theta, new Point3d(0.0, 0.0, 0.0));
		lsa.setCoordinate((int)theta + 1, new Point3d(v.getA(), v.getB(), v.getC()));

		Color3f[] colors = new Color3f[(int)theta + 2];
		Arrays.fill(colors, TurboColors.PINK);
		lsa.setColors(0, colors);
		Shape3D trailLines = new Shape3D(lsa);

		// polygons
		Point3d[] points = new Point3d[2 * pointsInit.length];
		for(int i = 0; i < pointsInit.length; i++)
			{
			points[i] = new Point3d(pointsInit[i]);
			points[points.length - 1 - i] = new Point3d(pointsInit[i]);
			}

		TriangleArray geometryArray = new TriangleArray(points.length * 3, GeometryArray.COORDINATES);
		for(int i = 0; i < points.length - 1; i++)
			{
			geometryArray.setCoordinate(3 * i, new Point3d(0.0f, 0.0f, 0.0f));
			geometryArray.setCoordinate(3 * i + 1, points[i]);
			geometryArray.setCoordinate(3 * i + 2, points[i + 1]);
			}

		GeometryInfo gi = new GeometryInfo(geometryArray);
		GeometryArray polygons = gi.getIndexedGeometryArray();

		// Appearance
		Appearance ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(color, 1);
		ap.setColoringAttributes(ca);
		TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST, 0.5f);
		ap.setTransparencyAttributes(ta);

		Shape3D trail = new Shape3D(polygons, ap);

		trailBG.detach();
		trailBG.addChild(trail);
		trailBG.addChild(trailLines);
		mainTG.addChild(trailBG);
		}

	public void removeTrail()
		{
		quaternionAxis.set(new Point3D(), new Point3D());
		trailBG.detach();
		trailBG.removeAllChildren();
		mainTG.addChild(trailBG);
		refresh();
		}

	private void createTrail(Matrix matrix, Vector3D shape)
		{
		Quaternion q1 = QuaternionTools.createRotationQuaternion(matrix.getGamma(), new Vector3D(1.0, 0.0, 0.0));
		Quaternion q2 = QuaternionTools.createRotationQuaternion(matrix.getBeta(), new Vector3D(0.0, 1.0, 0.0));
		Quaternion q3 = QuaternionTools.createRotationQuaternion(matrix.getAlpha(), new Vector3D(0.0, 0.0, 1.0));
		Vector3D tmp1 = QuaternionTools.rotation(shape, q1);
		Vector3D tmp2 = QuaternionTools.rotation(tmp1, q2);
		createTrail(q1, shape, TurboColors.RED);
		createTrail(q2, tmp1, TurboColors.GREEN);
		createTrail(q3, tmp2, TurboColors.BLUE);
		}

	public void rotate()
		{
		if (!isRunning)
			{
			isRunning = true;
			Thread thread = new Thread(new Runnable()
				{

					@Override
					public void run()
						{

						if (selectedRotation instanceof Quaternion)
							{
							Thread thread = rotate((Quaternion)selectedRotation);
							thread.start();
							try
								{
								thread.join();
								}
							catch (InterruptedException e)
								{
								e.printStackTrace();
								}
							createTrail();
							isRunning = false;
							refresh();
							}
						else if (selectedRotation instanceof Matrix)
							{
							Matrix matrix = (Matrix)selectedRotation;
							Quaternion q1 = QuaternionTools.createRotationQuaternion(matrix.getGamma(), new Vector3D(1.0, 0.0, 0.0));
							Quaternion q2 = QuaternionTools.createRotationQuaternion(matrix.getBeta(), new Vector3D(0.0, 1.0, 0.0));
							Quaternion q3 = QuaternionTools.createRotationQuaternion(matrix.getAlpha(), new Vector3D(0.0, 0.0, 1.0));
							Thread thread1 = rotate(q1);
							Thread thread2 = rotate(q2);
							Thread thread3 = rotate(q3);
							try
								{
								thread1.start();
								thread1.join();
								thread2.start();
								thread2.join();
								thread3.start();
								thread3.join();
								}
							catch (InterruptedException e)
								{
								e.printStackTrace();
								}
							createTrail();
							isRunning = false;
							refresh();
							}
						}
				});
			thread.start();
			}
		}

	private Thread rotate(Quaternion q)
		{
		return new Thread(new Runnable()
			{

				@Override
				public void run()
					{
					double theta = QuaternionTools.getAngle(q);
					theta *= 180.0 / Math.PI;
					for(int i = 0; i < (int)theta; i++)
						{
						try
							{
							Thread.sleep(1000 / 27);
							}
						catch (InterruptedException e)
							{
							// NOP
							}
						slowRotate();
//						panel.repaint();
						refresh();
						addParallelepiped();
						}
					}

				private void slowRotate()
					{
					Vector3D axis = QuaternionTools.getAxis(q);
					Quaternion qslow = QuaternionTools.createRotationQuaternion(2.0 * Math.PI / 360.0, axis);
					if (selectedShape instanceof Vector3D)
						{
						((Vector3D)selectedShape).set(QuaternionTools.rotation((Vector3D)selectedShape, qslow));
						}
					else if (selectedShape instanceof Point3D)
						{
						((Point3D)selectedShape).set(QuaternionTools.rotation((Point3D)selectedShape, qslow));
						}
					else if (selectedShape instanceof Vertex3D)
						{
						((Vertex3D)selectedShape).setA(QuaternionTools.rotation(((Vertex3D)selectedShape).getA(), qslow));
						((Vertex3D)selectedShape).setB(QuaternionTools.rotation(((Vertex3D)selectedShape).getB(), qslow));
						}
					}
			});

		}

	private void createMouseNavigation()
		{
		// Mouse rotation rotates the view
		MouseRotate mouseRotate = new MouseRotate(mainTG);
		MouseZoom mouseZoom = new MouseZoom(mainTG);
		MouseTranslate mouseTranslate = new MouseTranslate(mainTG);

		BoundingSphere bs = new BoundingSphere();

		mouseRotate.setSchedulingBounds(bs);
		mouseZoom.setSchedulingBounds(bs);
		mouseTranslate.setSchedulingBounds(bs);

		mainBG.addChild(mouseRotate);
		mainBG.addChild(mouseZoom);
		mainBG.addChild(mouseTranslate);
		}

	private void setBackgroundColor(Color3f color)
		{
		Background background = new Background(color);
		BoundingSphere sphere = new BoundingSphere(new Point3d(0, 0, 0), 100000);
		background.setApplicationBounds(sphere);
		mainBG.addChild(background);
		}

	private void createAxisSystem()
		{
		shapesBG.detach();
		shapesBG.addChild(new Vector3D(1, 0, 0, TurboColors.RED, 3));
		shapesBG.addChild(new Vector3D(0, 1, 0, TurboColors.GREEN, 3));
		shapesBG.addChild(new Vector3D(0, 0, 1, TurboColors.BLUE, 3));
		shapesBG.addChild(new Vector3D(100, 0, 0, TurboColors.RED, 1));
		shapesBG.addChild(new Vector3D(0, 100, 0, TurboColors.GREEN, 1));
		shapesBG.addChild(new Vector3D(0, 0, 100, TurboColors.BLUE, 1));
		mainTG.addChild(shapesBG);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private BranchGroup mainBG = new BranchGroup();
	private BranchGroup shapesBG = new BranchGroup();
	private BranchGroup paraBG = new BranchGroup();
	private BranchGroup trailBG = new BranchGroup();
	private TransformGroup mainTG = new TransformGroup();
	private SimpleUniverse universe;
	private Shape3D selectedShape;
	private RotationItem selectedRotation;
	private Vertex3D quaternionAxis;
	private Vertex3D[] para;
	private boolean isRunning = false;
	private JPanelMain2D panel;
	}
