package com.bb.cotacao.concurrent;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

import com.bb.cotacao.model.Cotacao;

public class ManagedThreadLocal {
	private static ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(WeakHashMap::new);
	private static final String KEY_ID = "___ID_@RD__";
	private static final String KEY_COTACAO = "___@RD_Cotacao__";

	public ManagedThreadLocal(final List<Cotacao> optional) {
		setCurrentCotacao(optional);
	}

	public static String getTraceId() {
		if (ManagedThreadLocal.CONTEXT.get().get(KEY_ID) == null) {
			ManagedThreadLocal.CONTEXT.get().put(KEY_ID, UUID.randomUUID().toString());
		}
		return (String) ManagedThreadLocal.CONTEXT.get().get(KEY_ID);
	}

	public static Optional<Cotacao> getCurrentCotacao() {
		return Optional.ofNullable((Cotacao) ManagedThreadLocal.CONTEXT.get().get(KEY_COTACAO));
	}

	public static void setCurrentCotacao(final List<Cotacao> existing) {
		// existing.ifPresent(p -> ManagedThreadLocal.CONTEXT.get().put(KEY_COTACAO,
		// p));
	}

	public static void clear() {
		ManagedThreadLocal.CONTEXT.get().clear();
		ManagedThreadLocal.CONTEXT.remove();
	}
}
