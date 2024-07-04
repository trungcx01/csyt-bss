import dayjs from 'dayjs';

export interface ICsytOneBss {
  id?: number;
  maCsyt?: string;
  maThuebao?: string;
  maDuan?: string;
  trangthai?: number;
  thoigianCapnhat?: dayjs.Dayjs;
  maTinh?: string | null;
  maSpdv?: string | null;
  maHopdong?: string | null;
  ngaykyHopdong?: dayjs.Dayjs | null;
  hieulucTu?: dayjs.Dayjs | null;
  hieulucDen?: dayjs.Dayjs | null;
  tenKhachhang?: string | null;
  diachiKhachhang?: string | null;
  mota?: string | null;
  lastUser?: string | null;
  lastModified?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ICsytOneBss> = {};
