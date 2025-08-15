import axios from 'axios';
import ApiConfig from '@/config/api-config';
import { Author } from '@/types/author';

const jwtToken = () => localStorage.getItem('jwt-token') || '';

export const getAllAuthors = async () => {
  return axios.get<{ data: Author[] }>(ApiConfig.GET_ALL_AUTHOR, {
    headers: { Authorization: jwtToken() },
  });
};

export const addAuthor = async (author: Omit<Author, 'id'>) => {
  return axios.post(ApiConfig.ADD_AUTHOR, author, {
    headers: { Authorization: jwtToken() },
  });
};

export const updateAuthor = async (id: string, author: Partial<Author>) => {
  return axios.patch(`${ApiConfig.UPDATE_AUTHOR}/${id}`, author, {
    headers: { Authorization: jwtToken() },
  });
};

export const deleteAuthor = async (id: string) => {
  return axios.delete(`${ApiConfig.DELETE_AUTHOR}/${id}`, {
    headers: { Authorization: jwtToken() },
  });
};
