/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package fr.polytechtours.javaperformance.jmh;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import fr.polytechtours.javaperformance.jmh.sort.BubbleSort;
import fr.polytechtours.javaperformance.jmh.sort.CombSort;
import fr.polytechtours.javaperformance.jmh.sort.GnomeSort;
import fr.polytechtours.javaperformance.jmh.sort.InsertionSort;
import fr.polytechtours.javaperformance.jmh.sort.JavaSort;
import fr.polytechtours.javaperformance.jmh.sort.SelectionSort;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
public class MyBenchmark {

	// ===== ATTRIBUTES =====
	
	private static BubbleSort bubbleSort = new BubbleSort();
	private static CombSort combSort = new CombSort();
	private static GnomeSort gnomeSort = new GnomeSort();
	private static InsertionSort insertionSort = new InsertionSort();
	private static JavaSort javaSort = new JavaSort();
	private static SelectionSort selectionSort = new SelectionSort();
	
	@State(Scope.Thread)
	public static class Data {
		
		@SuppressWarnings("unused")
		private ArrayList<Integer> tab;
		
		public Data() {
			tab = new ArrayList<Integer>();
			tab.add(1);
			tab.add(5);
			tab.add(4);
			tab.add(2);
			tab.add(3);
		}
		
		public ArrayList<Integer> getTab() {
			return this.tab;
		}
		
		public void resetTab() {
			tab.clear();
			tab.add(1);
			tab.add(5);
			tab.add(4);
			tab.add(2);
			tab.add(3);
		}
		
		@TearDown(Level.Iteration)
		public void displayTab() {
			System.out.println(tab.toString());
		}
	}

	// ======================
	
	// ===== BENCHMARKS =====
	
	@Benchmark
	public void benchmarkBubbleSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		data.resetTab();
		blackhole.consume(bubbleSort.sort(data.getTab()));
	}
	
	@Benchmark
	public void benchmarkCombSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		data.resetTab();
		blackhole.consume(combSort.sort(data.getTab()));
	}
	
	@Benchmark
	public void benchmarkGnomeSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		data.resetTab();
		blackhole.consume(gnomeSort.sort(data.getTab()));
	}
	
	@Benchmark
	public void benchmarkInsertionSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		blackhole.consume(insertionSort.sort(data.getTab()));
		data.resetTab();
	}
	
	@Benchmark
	public void benchmarkJavaSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		data.resetTab();
		blackhole.consume(javaSort.sort(data.getTab()));
	}
	
	@Benchmark
	public void benchmarkSelectionSort(Data data, Blackhole blackhole) {
		
		// Sort the list and consume it
		data.resetTab();
		blackhole.consume(selectionSort.sort(data.getTab()));
	}
	
	// ======================
}
