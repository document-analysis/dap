package org.daf.data_structures;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.daf.common.DafException;

/**
 * 
 *
 * <p>
 * Date: 30 May 2017
 * @author Asher Stern
 *
 */
public final class Document implements Serializable, Iterable<Annotation<?>>
{
	public Document(String name, String text)
	{
		this(name, new DocumentCollection(), text);
	}
	
	public Document(String name, DocumentCollection documentCollection, String text)
	{
		if (null==name) {throw new DafException("Null name");}
		if (null==documentCollection) {throw new DafException("Null documentCollection");}
		if (null==text) {throw new DafException("Null text");}
		
		synchronized(documentCollection)
		{
			this.name = name;
			this.documentCollection = documentCollection;
			this.documentCollection.addDocument(this.name, this);
		}
		
		this.text = text;
	}
	

	public DocumentCollection getDocumentCollection()
	{
		return documentCollection;
	}
	
	public synchronized void setFeature(String name, Feature feature)
	{
		if (null==name) {throw new DafException("Null name");}
		if (null==feature) {throw new DafException("Null feature");}
		features.put(name, feature);
	}
	
	public synchronized void removeFeature(String name)
	{
		if (null==name) {throw new DafException("Null name");}
		features.remove(name);
	}
	
	public synchronized Map<String, Feature> getFeatures()
	{
		return Collections.unmodifiableMap(features);
	}
	
	public synchronized <T extends AnnotationContents> T addAnnotation(int begin, int end, T annotationContents)
	{
		if ((begin<0)||(end<0)||(end<begin)) {throw new DafException("Illegal begin / end value(s): begin="+begin+". end="+end+".");}
		if (end>text.length()) {throw new DafException("Illegal end value. end>text.length(). end="+end+". text.length()="+text.length()+".");}
		if (null==annotationContents) {throw new DafException("Null annotationContents");}
		
		throw new DafException("Not yet implemented.");
	}
	
	public synchronized void removeAnnotation(Annotation<?> annotation)
	{
		if (null==annotation) {throw new DafException("Null annotation");}
		
		throw new DafException("Not yet implemented.");
	}
	
	public Iterator<Annotation<? extends AnnotationContents>> iterator(Class<? extends AnnotationContents> superClass, int begin, int end)
	{
		if ((begin<0)||(end<0)||(end<begin)) {throw new DafException("Illegal begin / end value(s): begin="+begin+". end="+end+".");}
		if (null==superClass) {throw new DafException("Null superClass");}
		
		throw new DafException("Not yet implemented.");
	}
	
	public Iterator<Annotation<? extends AnnotationContents>> iterator(Class<? extends AnnotationContents> superClass, int begin)
	{
		return iterator(superClass, begin, text.length());
	}
	
	public Iterator<Annotation<? extends AnnotationContents>> iterator(Class<? extends AnnotationContents> superClass)
	{
		return iterator(superClass, 0, text.length());
	}
	
	@Override
	public Iterator<Annotation<? extends AnnotationContents>> iterator()
	{
		return iterator(AnnotationContents.class);
	}
	
	public Iterable<Annotation<? extends AnnotationContents>> iterable(Class<? extends AnnotationContents> superClass, int begin, int end)
	{
		return ()->iterator(superClass, begin, end);
	}
	public Iterable<Annotation<? extends AnnotationContents>> iterable(Class<? extends AnnotationContents> superClass, int begin)
	{
		return ()->iterator(superClass, begin);
	}
	public Iterable<Annotation<? extends AnnotationContents>> iterable(Class<? extends AnnotationContents> superClass)
	{
		return ()->iterator(superClass);
	}
	
	
	
	



	private final String name;
	private final DocumentCollection documentCollection;
	private final String text;
	
	private final Map<String, Feature> features = new LinkedHashMap<>();
}
