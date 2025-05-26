import { createStore } from 'vuex'

export default createStore({
  state: {
    analysisSubject: null,
    analysisScoreId: null
  },
  getters: {
    
  },
  mutations: {
    setAnalysisSubject(state, subject) {
      state.analysisSubject = subject
    },
    setAnalysisScoreId(state, scoreId) {
      state.analysisScoreId = scoreId
    }
  },
  actions: {
  },
  modules: {
  }
})
