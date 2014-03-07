/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschr??nkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * generated by Xtext
 */
package org.eclipse.smarthome.model.rule;

import org.eclipse.smarthome.model.rule.scoping.RulesImplicitlyImportedTypes;
import org.eclipse.smarthome.model.script.interpreter.ScriptInterpreter;
import org.eclipse.smarthome.model.script.scoping.ActionClassLoader;
import org.eclipse.smarthome.model.script.scoping.ActionClasspathBasedTypeScopeProvider;
import org.eclipse.smarthome.model.script.scoping.ActionClasspathTypeProviderFactory;
import org.eclipse.smarthome.model.script.scoping.ScriptImportSectionNamespaceScopeProvider;
import org.eclipse.smarthome.model.script.scoping.StateAndCommandProvider;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.IGenerator.NullGenerator;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.interpreter.IExpressionInterpreter;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes;

import com.google.inject.Binder;
import com.google.inject.name.Names;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("restriction")
public class RulesRuntimeModule extends org.eclipse.smarthome.model.rule.AbstractRulesRuntimeModule {

	public Class<? extends ImplicitlyImportedTypes> bindImplicitlyImportedTypes() {
		return RulesImplicitlyImportedTypes.class;
	}

	public Class<StateAndCommandProvider> bindStateAndCommandProvider() {
		return StateAndCommandProvider.class;
	}
	
	/* we need this so that our pluggable actions can be resolved at design time */
	@Override
	public Class<? extends org.eclipse.xtext.common.types.access.IJvmTypeProvider.Factory> bindIJvmTypeProvider$Factory() {
		return ActionClasspathTypeProviderFactory.class;
	}
	
	/* we need this so that our pluggable actions can be resolved when being parsed at runtime */
	@Override
	public Class<? extends org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider> bindAbstractTypeScopeProvider() {
		return ActionClasspathBasedTypeScopeProvider.class;
	}

	/* we need this so that our pluggable actions can be resolved when being executed at runtime */
	@Override
	public ClassLoader bindClassLoaderToInstance() {
		return new ActionClassLoader(getClass().getClassLoader());
	}
	
	@Override
	public Class<? extends IGenerator> bindIGenerator() {
		return NullGenerator.class;
	}
	
	public Class<? extends IExpressionInterpreter> bindIExpressionInterpreter() {
		return ScriptInterpreter.class;
	}
	
	public void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(ScriptImportSectionNamespaceScopeProvider.class);
	}
	
}
